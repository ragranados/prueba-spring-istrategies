package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.PeliculaService;
import com.prueba.istrategiesspring.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

class LikesComparator implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula o1, Pelicula o2) {
        return o2.getMeGustas().size() - o1.getMeGustas().size();
    }
}

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private UsuarioService usuarioService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Pelicula pelicula) {

        ServiceResponse serviceResponse = peliculaService.crearPelicula(pelicula);

        return ResponseEntity.ok("Pelicula registrada con exito");
    }

    @GetMapping("/disponibles")
    public ResponseEntity disponibles() {

        ServiceResponse serviceResponse = peliculaService.obtenerPeliculasFiltro(true);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());

    }

    @GetMapping("/buscar")
    public ResponseEntity obtenerPorNombre(@RequestParam String nombre) {

        ServiceResponse serviceResponse = peliculaService.obtenerPeliculaPorNombre(nombre);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());

    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/todas")
    public ResponseEntity obtenerTodas(@RequestParam Optional<Boolean> disponible, Optional<Boolean> ordenarLikes) {

        ServiceResponse serviceResponse;

        if (!disponible.isPresent()) {
            serviceResponse = peliculaService.obtenerPeliculas();
        } else {
            serviceResponse = peliculaService.obtenerPeliculasFiltro(disponible.get());
        }

        if (ordenarLikes.isPresent() && ordenarLikes.get()) {
            Collections.sort((List<Pelicula>) serviceResponse.getData(), new LikesComparator());
        }

        return ResponseEntity.ok(serviceResponse.getData());

    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody Pelicula pelicula) {

        ServiceResponse serviceResponse = peliculaService.actualizarPelicula(pelicula);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok("Datos actualizados con exito");

    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping("/cambiarDisponibilidad/{id}")
    public ResponseEntity cambiarDisponibilidad(@PathVariable(value = "id") Long id, @RequestBody boolean disponibilidad) {

        ServiceResponse pelicula = peliculaService.obtenerPeliculaPorId(id);

        if (!pelicula.getStatus()) {
            return ResponseEntity.status(400).body(pelicula.getMessage());
        }

        ServiceResponse serviceResponse = peliculaService.cambiarDisponibilidad((Pelicula) pelicula.getData(), disponibilidad);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok("Cambios realizados con exito");

    }

    @PatchMapping("/agregarLike/{id}")
    public ResponseEntity agregarLike(@PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) usuarioService.encontrarPorEmail(authentication.getName()).getData();

        ServiceResponse serviceResponsePelicula = peliculaService.obtenerPeliculaPorId(id);

        if (!serviceResponsePelicula.getStatus()) {
            return ResponseEntity.status(400).body("Pelicula no encontrada");
        }

        ServiceResponse serviceResponse = peliculaService.addLike((Pelicula) serviceResponsePelicula.getData(), usuario);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());

    }
}

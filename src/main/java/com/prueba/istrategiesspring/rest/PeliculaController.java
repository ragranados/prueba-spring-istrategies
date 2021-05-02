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

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private UsuarioService usuarioService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Pelicula pelicula){
        try {
            ServiceResponse serviceResponse = peliculaService.crearPelicula(pelicula);

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return ResponseEntity.ok("Pelicula registrada con exito");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/disponibles")
    public ResponseEntity disponibles(){
        try {
            ServiceResponse serviceResponse = peliculaService.obtenerPeliculasDisponibles();

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return  ResponseEntity.ok(serviceResponse.getData());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody Pelicula pelicula){
        try {
            ServiceResponse serviceResponse = peliculaService.actualizarPelicula(pelicula);

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return ResponseEntity.ok("Datos actualizados con exito");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping("/cambiarDisponibilidad/{id}")
    public ResponseEntity cambiarDisponibilidad(@PathVariable(value = "id") Long id, @RequestBody boolean disponibilidad){
        try {
            ServiceResponse pelicula = peliculaService.obtenerPeliculaPorId(id);

            if(!pelicula.getStatus()){
                return ResponseEntity.status(400).body(pelicula.getMessage());
            }

            ServiceResponse serviceResponse = peliculaService.cambiarDisponibilidad((Pelicula) pelicula.getData(), disponibilidad);

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return ResponseEntity.ok("Cambios realizados con exito");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/agregarLike/{id}")
    public ResponseEntity agregarLike(@PathVariable(value = "id") Long id){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = (Usuario) usuarioService.encontrarPorEmail(authentication.getName()).getData();

            ServiceResponse serviceResponsePelicula = peliculaService.obtenerPeliculaPorId(id);

            if(!serviceResponsePelicula.getStatus()){
                return ResponseEntity.status(400).body("Pelicula no encontrada");
            }

            ServiceResponse serviceResponse = peliculaService.addLike((Pelicula) serviceResponsePelicula.getData() ,usuario);

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return ResponseEntity.ok(serviceResponse.getData());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }
}

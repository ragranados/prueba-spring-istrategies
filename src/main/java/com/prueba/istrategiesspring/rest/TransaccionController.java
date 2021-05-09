package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Compra;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.Transaccion;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.requests.TransaccionRequest;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.PeliculaService;
import com.prueba.istrategiesspring.services.TransaccionService;
import com.prueba.istrategiesspring.services.UserDetailService;
import com.prueba.istrategiesspring.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/guardar")
    //@RequestMapping("/guardar")
    public ResponseEntity guardar(@RequestBody TransaccionRequest transaccionRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = (Usuario) usuarioService.encontrarPorEmail(authentication.getName()).getData();

        ServiceResponse serviceResponse = transaccionService.crearTransaccion(usuario, transaccionRequest.getCompras(), transaccionRequest.getAlquiler());

        return ResponseEntity.ok("Transaccion realizada con exito");

    }

    @GetMapping("/transacciones/me")
    //@RequestMapping("/transacciones/me")
    public ResponseEntity obtenerMisTransacciones() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) usuarioService.encontrarPorEmail(authentication.getName()).getData();

        ServiceResponse serviceResponse = transaccionService.obtenerTransaccionesPorIdUsuario(usuario.getId());

        return ResponseEntity.ok(serviceResponse.getData());
    }
}

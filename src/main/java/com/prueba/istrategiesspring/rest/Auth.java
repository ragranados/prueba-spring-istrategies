package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.UserDetailService;
import com.prueba.istrategiesspring.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class Auth {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticactionManager;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@RequestBody Usuario usuario){
        ServiceResponse exist = usuarioService.encontrarPorEmail(usuario.getEmail());

        if(exist.getStatus()){
            return ResponseEntity.status(404).body("El correo ya se encuentra registrado");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

        ServiceResponse nUsuario = usuarioService.registrarUsuario(usuario);

        if(!nUsuario.getStatus()){
            return ResponseEntity.status(404).body(nUsuario.getMessage());
        }

        return ResponseEntity.ok("Usuario registrado con exito");
    }
}

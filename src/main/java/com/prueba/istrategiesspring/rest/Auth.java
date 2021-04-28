package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.LoginForm;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.LoginResponse;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.UserDetailService;
import com.prueba.istrategiesspring.services.UsuarioService;
import com.prueba.istrategiesspring.utils.JwtUtil;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/auth")
@RestController
public class Auth {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticactionManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm){

        ServiceResponse serviceResponse = usuarioService.encontrarPorEmail(loginForm.getEmail());

        if(!serviceResponse.getStatus()){
            return ResponseEntity.status(400).body("Usuario no encontrado");
        }

        try{
            authenticactionManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword())
            );
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(loginForm.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("jwt", jwt);
        System.out.println(jsonObject);

        return ResponseEntity.ok(new LoginResponse(jwt, (Usuario) serviceResponse.getData()));

        /*HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("jwt", jwt);
        hashMap.put("user", serviceResponse.getData());

        return ResponseEntity.ok(hashMap);*/
    }
}

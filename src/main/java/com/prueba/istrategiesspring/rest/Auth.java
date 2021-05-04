package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.EmailServiceImpl;
import com.prueba.istrategiesspring.models.LoginForm;
import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.models.TokenActivacion;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.requests.ChangeRoleRequest;
import com.prueba.istrategiesspring.responses.LoginResponse;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.RoleService;
import com.prueba.istrategiesspring.services.TokenActivacionService;
import com.prueba.istrategiesspring.services.UserDetailService;
import com.prueba.istrategiesspring.services.UsuarioService;
import com.prueba.istrategiesspring.utils.JwtUtil;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/auth")
@RestController
public class Auth {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticactionManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private TokenActivacionService tokenActivacionService;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@RequestBody Usuario usuario){
        usuario.setEmail(usuario.getEmail().toLowerCase());
        ServiceResponse exist = usuarioService.encontrarPorEmail(usuario.getEmail());

        if(exist.getStatus()){
            return ResponseEntity.status(404).body("El correo ya se encuentra registrado");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

        Role role = new Role(2l, "Usuario");

        usuario.setRole(role);

        ServiceResponse nUsuario = usuarioService.registrarUsuario(usuario);

        if(!nUsuario.getStatus()){
            return ResponseEntity.status(404).body(nUsuario.getMessage());
        }

        //TokenActivacion tokenActivacion = (TokenActivacion) nUsuario.getData();

        /*emailService.emailActivarCuenta(
                usuario.getEmail(),
                "Activacion cuenta",
                tokenActivacion.getToken()
        );*/

        return ResponseEntity.ok("Usuario registrado con exito");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm){
        loginForm.setEmail(loginForm.getEmail().toLowerCase());

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

    @Secured({"ROLE_ADMIN"})
    @PatchMapping("/cambiarRol") ResponseEntity modificarUsuario (@RequestBody ChangeRoleRequest changeRoleRequest){
        try {
            ServiceResponse serviceResponseRole = roleService.obtenerRolePorId(changeRoleRequest.getNuevoRolId());

            if(!serviceResponseRole.getStatus()){
                return ResponseEntity.status(400).body("Rol no encontrado");
            }

            ServiceResponse serviceResponseUsuario = usuarioService.encontrarPorId(changeRoleRequest.getIdUsuario());

            if(!serviceResponseUsuario.getStatus()){
                return ResponseEntity.status(400).body("Usuario no encontrado");
            }

            Usuario usuario = (Usuario) serviceResponseUsuario.getData();

            usuario.setRole((Role) serviceResponseRole.getData());

            usuarioService.modificarUsuario(usuario);

            return ResponseEntity.ok(changeRoleRequest);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

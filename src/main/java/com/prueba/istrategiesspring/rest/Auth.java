package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.EmailServiceImpl;
import com.prueba.istrategiesspring.dto.Request.LoginForm;
import com.prueba.istrategiesspring.dto.UsuarioDTO;
import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.dto.Request.ChangeRoleRequest;
import com.prueba.istrategiesspring.dto.Response.LoginResponse;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.RoleService;
import com.prueba.istrategiesspring.services.TokenActivacionService;
import com.prueba.istrategiesspring.services.UserDetailService;
import com.prueba.istrategiesspring.services.UsuarioService;
import com.prueba.istrategiesspring.utils.JwtUtil;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@Valid @RequestBody LoginForm loginForm){

        System.out.println(loginForm.toString());

        Usuario usuario = new Usuario();

        usuario.setEmail(loginForm.getEmail());

        loginForm.setEmail(usuario.getEmail().toLowerCase());
        ServiceResponse exist = usuarioService.encontrarPorEmail(usuario.getEmail());

        if(exist.getStatus()){
            return ResponseEntity.status(404).body("El correo ya se encuentra registrado");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        usuario.setPassword(bCryptPasswordEncoder.encode(loginForm.getPassword()));

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
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm){
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

        Usuario usuario = (Usuario) serviceResponse.getData();
        System.out.println(usuario.getPassword());

        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

        return ResponseEntity.ok(new LoginResponse(jwt, usuarioDTO));

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

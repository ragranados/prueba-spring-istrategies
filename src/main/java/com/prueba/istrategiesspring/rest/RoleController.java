package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Role role){
        try{
            ServiceResponse serviceResponse = roleService.crearRole(role);

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return  ResponseEntity.ok(serviceResponse.getData());
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        try{
            ServiceResponse serviceResponse = roleService.listarRoles();

            if(!serviceResponse.getStatus()){
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return  ResponseEntity.ok(serviceResponse.getData());
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Role role) {

        ServiceResponse serviceResponse = roleService.crearRole(role);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());

    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {

        ServiceResponse serviceResponse = roleService.listarRoles();

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());

    }
}

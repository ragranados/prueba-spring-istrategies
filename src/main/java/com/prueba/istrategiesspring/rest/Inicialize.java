package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Inicialize {
    @Autowired
    RoleService roleService;

    @GetMapping("/inicializar")
    public String inicializar(){
        ServiceResponse serviceResponse = roleService.listarRoles();

        if(serviceResponse.getStatus()){
            return "Los datos ya han sido iniciliazados";
        }

        try{
            Role role1 = new Role(1l, "Admin");
            Role role2 = new Role(2l, "Usuario");

            roleService.crearRole(role1);
            roleService.crearRole(role2);

        }catch (Exception e){
            return "No se pudo inicializar la app";
        }



        return "Datos insertados";
    }
}

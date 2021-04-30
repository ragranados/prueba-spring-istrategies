package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.PeliculaService;
import com.prueba.istrategiesspring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Inicialize {
    @Autowired
    RoleService roleService;

    @Autowired
    PeliculaService peliculaService;

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

            Pelicula pelicula = new Pelicula(
                    "El viaje de Chihiro",
                    "Chihiro se adentra en un mundo magico reinado por una bruja, donde quienes no obedecen son transformados en animales",
                    "URL imagen",
                    0,
                    2.50f,
                    20.00f,
                    true
            );

            Pelicula pelicula2 = new Pelicula(
                    "Rescatando al soldado Ryan",
                    "Ocho soldados arriesgan su vida en territorio aleman durante la Segunda Guerra Mundial para buscar a un soldado y regresarlo a casa",
                    "URL imagen",
                    0,
                    2.50f,
                    20.00f,
                    true
            );

            peliculaService.crearPelicula(pelicula);
            peliculaService.crearPelicula(pelicula2);

        }catch (Exception e){
            return "No se pudo inicializar la app";
        }

        return "Datos insertados";
    }
}

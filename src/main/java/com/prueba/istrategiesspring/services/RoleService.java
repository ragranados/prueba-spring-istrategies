package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dto.RoleDTO;
import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDTO roleDTO;

    public ServiceResponse crearRole(Role role){
        try{
            Role newRole = roleDTO.save(role);
            return new ServiceResponse (true, "Ok", newRole);
        }catch(Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse listarRoles(){
        try {
            List<Role> roles= roleDTO.findAll();

            if(roles.isEmpty()){
                return new ServiceResponse(false, "No se obtuvieron resultados", roles);
            }

            return new ServiceResponse(true, "Ok    ", roles);
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

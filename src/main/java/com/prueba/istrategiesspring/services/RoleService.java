package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dto.RoleDTO;
import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

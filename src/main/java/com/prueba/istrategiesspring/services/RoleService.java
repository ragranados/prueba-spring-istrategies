package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.constants.ExceptionsLabels;
import com.prueba.istrategiesspring.dao.RoleDAO;
import com.prueba.istrategiesspring.exceptions.NotFoundException;
import com.prueba.istrategiesspring.models.Role;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDTO;

    public ServiceResponse crearRole(Role role) {

        Role newRole = roleDTO.save(role);
        return new ServiceResponse(true, "Ok", newRole);

    }

    public ServiceResponse listarRoles() {

        List<Role> roles = roleDTO.findAll();

        if (roles.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok    ", roles);

    }

    public ServiceResponse obtenerRolePorId(Long id) {

        Optional<Role> role = roleDTO.findById(id);

        if (!role.isPresent()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok    ", role.get());

    }
}

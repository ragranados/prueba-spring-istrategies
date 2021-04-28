package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.UsuarioDAO;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDTO;

    public ServiceResponse registrarUsuario(Usuario usuario){
        try {
            Usuario nuevoUsuario = usuarioDTO.save(usuario);

            return new ServiceResponse(true, "Usuario insertado con exito", nuevoUsuario);
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse encontrarPorEmail(String email){
        try {
            Usuario usuario = usuarioDTO.findByEmail(email);
            System.out.println("Email en servicio " + usuario);

            if(usuario == null){
                return new ServiceResponse(false, "Usuario no encontrado", null);
            }

            return new ServiceResponse(true, "Usuario encontrado con exito", usuario);
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

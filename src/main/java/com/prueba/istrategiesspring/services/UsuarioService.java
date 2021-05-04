package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.UsuarioDAO;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDTO;

    @Autowired
    TokenActivacionService tokenActivacionService;

    @Transactional
    public ServiceResponse registrarUsuario(Usuario usuario){
        try {
            Usuario nuevoUsuario = usuarioDTO.save(usuario);

            //ServiceResponse token = tokenActivacionService.crearToken(nuevoUsuario);

            return new ServiceResponse(true, "Usuario insertado con exito", nuevoUsuario);
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse encontrarPorEmail(String email){
        try {
            Usuario usuario = usuarioDTO.findByEmail(email);

            if(usuario == null){
                return new ServiceResponse(false, "Usuario no encontrado", null);
            }

            return new ServiceResponse(true, "Usuario encontrado con exito", usuario);
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse encontrarPorId(Long id){
        try {
            Optional<Usuario> usuario = usuarioDTO.findById(id);

            if(!usuario.isPresent()){
                return new ServiceResponse(false, "Usuario no encontrado", null);
            }

            return new ServiceResponse(true, "Usuario encontrado con exito", usuario.get());
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse modificarUsuario (Usuario usuario){
        try {
            Usuario nUsuario = usuarioDTO.save(usuario);

            if(nUsuario == null){
                return new ServiceResponse(false, "No se pudo modificar", null);
            }

            return new ServiceResponse(true, "Usuario modificado con exito", nUsuario);

        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

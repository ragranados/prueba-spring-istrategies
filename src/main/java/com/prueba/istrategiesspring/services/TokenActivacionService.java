package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.TokenActivacionDAO;
import com.prueba.istrategiesspring.models.TokenActivacion;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class TokenActivacionService {

    @Autowired
    private TokenActivacionDAO tokenActivacionDAO;

    public ServiceResponse crearToken(Usuario usuario){
        try {
            String newToken = UUID.randomUUID().toString();

            TokenActivacion tokenActivacion = new TokenActivacion();
            tokenActivacion.setToken(newToken);
            tokenActivacion.setExpriryDate(LocalDate.now());
            tokenActivacion.setUsuario(usuario);

            TokenActivacion nTokenActivacion = tokenActivacionDAO.save(tokenActivacion);

            if(nTokenActivacion == null){
                return new ServiceResponse(false, "No se pudo create el token", null);
            }

            return new ServiceResponse(true, "Token creado con exito", nTokenActivacion);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse obtenerToken(String token){
        try {
            TokenActivacion tokenActivacion = tokenActivacionDAO.findByToken(token);

            if(tokenActivacion == null){
                return new ServiceResponse(false, "No se pudo create el token", null);
            }

            return new ServiceResponse(true, "Ok", tokenActivacion);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

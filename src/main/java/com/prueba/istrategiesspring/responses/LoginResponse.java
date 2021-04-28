package com.prueba.istrategiesspring.responses;

import com.prueba.istrategiesspring.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LoginResponse {
    private String jwt;
    private Usuario usuario;

    public LoginResponse() { }

    public LoginResponse(String jwt, Usuario usuario) {
        this.jwt = jwt;
        this.usuario = usuario;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

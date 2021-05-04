package com.prueba.istrategiesspring.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TokenActivacion {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario usuario;

    @Column
    private LocalDate expriryDate;

    public TokenActivacion(Long id, String token, Usuario usuario, LocalDate expriryDate) {
        this.id = id;
        this.token = token;
        this.usuario = usuario;
        this.expriryDate = expriryDate;
    }

    public TokenActivacion() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getExpriryDate() {
        return expriryDate;
    }

    public void setExpriryDate(LocalDate expriryDate) {
        this.expriryDate = expriryDate;
    }
}

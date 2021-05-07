package com.prueba.istrategiesspring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Role {
    @Id
    private Long id;

    @Column
    private String nombre;

    public Role() { }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

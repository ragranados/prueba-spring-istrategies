package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@JsonIgnoreProperties({"transacciones", "peliculasGustadas"})
@Entity
@Setter
@Getter
@ToString
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column(name = "enabled", columnDefinition = "boolean default false")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @OneToMany(targetEntity = Transaccion.class, mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaccion> transacciones;

    @ManyToMany
    @JoinTable(
            name = "pelicula_like",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    private List<Pelicula> peliculasGustadas;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}

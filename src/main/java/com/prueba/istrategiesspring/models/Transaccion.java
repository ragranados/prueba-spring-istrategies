package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@JsonIgnoreProperties({"usuario"})
@Entity
public class Transaccion {

    @Id
    @GeneratedValue
    private Long id;

    @Nullable
    @OneToOne(mappedBy = "transaccion")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}

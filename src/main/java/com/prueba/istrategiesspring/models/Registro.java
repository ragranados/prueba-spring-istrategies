package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties("transaccion")
@Entity
@Setter
@Getter
public class Registro {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column
    private LocalDate fecha;

    @Column
    private int numeroPeliculas;

    @Column
    private String tipo;


}

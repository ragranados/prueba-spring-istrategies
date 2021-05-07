package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"transaccion"})
@Entity
@Setter
@Getter
public class Alquiler {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    @Column(columnDefinition = "boolean default false")
    private boolean devuelto;

    @Column
    private LocalDate fechaAlquiler;

    @Column
    private LocalDate fechaDevolucion;

}

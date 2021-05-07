package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonIgnoreProperties({"pelicula"})
@Entity
@Setter
@Getter
public class RegistroActualizacionesPelicula {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String tituloAnterior;

    @Column
    private BigDecimal precioAlquilerAnterior;

    @Column
    private BigDecimal precioCompraAnterior;

    @Column
    private String titulo;

    @Column
    private BigDecimal precioAlquiler;

    @Column
    private BigDecimal precioCompra;

    @ManyToOne
    @JoinColumn(name="pelicula_id")
    private Pelicula pelicula;
}

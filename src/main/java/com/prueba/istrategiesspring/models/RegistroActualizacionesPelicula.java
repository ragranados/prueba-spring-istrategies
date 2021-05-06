package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonIgnoreProperties({"pelicula"})
@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloAnterior() {
        return tituloAnterior;
    }

    public void setTituloAnterior(String tituloAnterior) {
        this.tituloAnterior = tituloAnterior;
    }

    public BigDecimal getPrecioAlquilerAnterior() {
        return precioAlquilerAnterior;
    }

    public void setPrecioAlquilerAnterior(BigDecimal precioAlquilerAnterior) {
        this.precioAlquilerAnterior = precioAlquilerAnterior;
    }

    public BigDecimal getPrecioCompraAnterior() {
        return precioCompraAnterior;
    }

    public void setPrecioCompraAnterior(BigDecimal precioCompraAnterior) {
        this.precioCompraAnterior = precioCompraAnterior;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(BigDecimal precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}

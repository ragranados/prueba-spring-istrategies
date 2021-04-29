package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"pelicula"})
@Entity
public class RegistroActualizacionesPelicula {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String tituloAnterior;

    @Column
    private Float precioAlquilerAnterior;

    @Column
    private Float precioCompraAnterior;

    @Column
    private String titulo;

    @Column
    private Float precioAlquiler;

    @Column
    private Float precioCompra;

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

    public Float getPrecioAlquilerAnterior() {
        return precioAlquilerAnterior;
    }

    public void setPrecioAlquilerAnterior(Float precioAlquilerAnterior) {
        this.precioAlquilerAnterior = precioAlquilerAnterior;
    }

    public Float getPrecioCompraAnterior() {
        return precioCompraAnterior;
    }

    public void setPrecioCompraAnterior(Float precioCompraAnterior) {
        this.precioCompraAnterior = precioCompraAnterior;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Float precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public Float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}

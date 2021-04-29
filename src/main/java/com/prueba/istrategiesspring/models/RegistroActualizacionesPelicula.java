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

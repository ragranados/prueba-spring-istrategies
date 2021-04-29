package com.prueba.istrategiesspring.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private String imagen;

    @Column(columnDefinition = "INT default 0")
    private int stock;

    @Column
    private Float precioAlquiler;

    @Column
    private Float precioCompra;

    @Column
    private boolean disponible;

    @OneToMany(targetEntity = RegistroActualizacionesPelicula.class, mappedBy = "pelicula", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RegistroActualizacionesPelicula> registroActualizaciones;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<RegistroActualizacionesPelicula> getRegistroActualizaciones() {
        return registroActualizaciones;
    }

    public void setRegistroActualizaciones(List<RegistroActualizacionesPelicula> registroActualizaciones) {
        this.registroActualizaciones = registroActualizaciones;
    }
}

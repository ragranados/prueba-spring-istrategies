package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties({"compras"})
@Entity
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private String imagen;

    @Column(columnDefinition = "INT default 0")
    private int stockAlquiler;

    @Column(columnDefinition = "INT default 0")
    private int stockCompra;

    @Column
    private BigDecimal precioAlquiler;

    @Column
    private BigDecimal precioCompra;

    @Column
    private boolean disponible;

    @OneToMany(targetEntity = RegistroActualizacionesPelicula.class, mappedBy = "pelicula", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RegistroActualizacionesPelicula> registroActualizaciones;

    @ManyToMany(mappedBy = "peliculasGustadas")
    private List<Usuario> meGustas;

    public Pelicula() { }

    public Pelicula(String titulo, String descripcion, String imagen, int stockAlquiler, int stockCompra, BigDecimal precioAlquiler, BigDecimal precioCompra, boolean disponible) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.stockAlquiler = stockAlquiler;
        this.stockCompra = stockCompra;
        this.precioAlquiler = precioAlquiler;
        this.precioCompra = precioCompra;
        this.disponible = disponible;
    }

    @ManyToMany(mappedBy = "peliculas")
    private List<Compra> compras;

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

    public int getStockAlquiler() {
        return stockAlquiler;
    }

    public void setStockAlquiler(int stockAlquiler) {
        this.stockAlquiler = stockAlquiler;
    }

    public int getStockCompra() {
        return stockCompra;
    }

    public void setStockCompra(int stockCompra) {
        this.stockCompra = stockCompra;
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

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Usuario> getMeGustas() {
        return meGustas;
    }

    public void setMeGustas(List<Usuario> meGustas) {
        this.meGustas = meGustas;
    }
}

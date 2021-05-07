package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties({"compras"})
@Entity
@Setter
@Getter
@NoArgsConstructor
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

}

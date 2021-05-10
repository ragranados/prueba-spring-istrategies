package com.prueba.istrategiesspring.dto;

import com.prueba.istrategiesspring.models.RegistroActualizacionesPelicula;
import com.prueba.istrategiesspring.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {
    private Long id;

    private String titulo;

    private String descripcion;

    private String imagen;

    private int stockAlquiler;

    private int stockCompra;

    private BigDecimal precioAlquiler;

    private BigDecimal precioCompra;

    private boolean disponible;

    private List<RegistroActualizacionesPelicula> registroActualizaciones;

    //private List<Usuario> meGustas;

    public PeliculaDTO(String titulo, String descripcion, String imagen, int stockAlquiler, int stockCompra, BigDecimal precioAlquiler, BigDecimal precioCompra, boolean disponible) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.stockAlquiler = stockAlquiler;
        this.stockCompra = stockCompra;
        this.precioAlquiler = precioAlquiler;
        this.precioCompra = precioCompra;
        this.disponible = disponible;
    }
}

package com.prueba.istrategiesspring.dto;

import com.prueba.istrategiesspring.models.RegistroActualizacionesPelicula;
import com.prueba.istrategiesspring.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {
    private Long id;

    @NotNull(message = "El titulo es requerido")
    @NotBlank(message = "El titulo no debe ser una cadena en blanco")
    private String titulo;

    @NotBlank(message = "la descripcion es requerida")
    @NotBlank(message = "La descripcion no debe ser una cadena en blanco")
    private String descripcion;

    private String imagen;

    @DecimalMin(value = "0", inclusive = true, message = "stockAlquiler debe ser mayor a 0")
    private int stockAlquiler;

    @DecimalMin(value = "0", inclusive = true, message = "stockCompra debe ser mayor a 0")
    private int stockCompra;

    @NotNull(message = "precioAlquiler es requerido")
    @DecimalMin(value = "0", inclusive = true, message = "precioAlquiler debe ser mayor a 0")
    private BigDecimal precioAlquiler;

    @NotNull(message = "precioCompra es requerido")
    @DecimalMin(value = "0", inclusive = true, message = "precioCompra debe ser mayor a 0")
    private BigDecimal precioCompra;

    private boolean disponible;

    private List<RegistroActualizacionesPelicula> registroActualizaciones;

    private List<UsuarioDTO> meGustas;

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

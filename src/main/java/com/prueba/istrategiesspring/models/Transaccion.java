package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"usuario"})
@Entity
public class Transaccion {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Float precioTotal;

    @Nullable
    @OneToOne(mappedBy = "transaccion")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Nullable
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(@Nullable Compra compra) {
        this.compra = compra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

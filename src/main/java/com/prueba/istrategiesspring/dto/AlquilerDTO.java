package com.prueba.istrategiesspring.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AlquilerDTO {

    private Long transaccion;
    private Long usuario;
    private LocalDate fecha;
    private Long numeroPeliculas;

    public AlquilerDTO(Long transaccion, Long usuario, LocalDate fecha, Long numeroPeliculas) {
        this.transaccion = transaccion;
        this.usuario = usuario;
        this.fecha = fecha;
        this.numeroPeliculas = numeroPeliculas;
    }

    public Long getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Long transaccion) {
        this.transaccion = transaccion;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getNumeroPeliculas() {
        return numeroPeliculas;
    }

    public void setNumeroPeliculas(Long numeroPeliculas) {
        this.numeroPeliculas = numeroPeliculas;
    }
}

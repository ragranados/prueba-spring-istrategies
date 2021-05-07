package com.prueba.istrategiesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties({"usuario"})
@Entity
@Setter
@Getter
public class Transaccion {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private BigDecimal precioTotal;

    @Nullable
    @OneToOne(mappedBy = "transaccion")
    private Compra compra;

    @OneToMany(mappedBy = "transaccion")
    private List<Alquiler> alquiler;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column
    private LocalDate fecha;

}

package com.prueba.istrategiesspring.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;*/

    @ManyToMany
    @JoinTable(
            name = "pelicula_compra",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private List<Pelicula> peliculas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    @Column
    private LocalDate fechaCompra;


}

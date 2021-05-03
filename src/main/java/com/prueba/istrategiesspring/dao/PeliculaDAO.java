package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaDAO extends JpaRepository<Pelicula, Long> {

    @Query("FROM Pelicula WHERE titulo like '%?1%' order by titulo")
    List<Pelicula> findByTitulo (@Param("titulo") String titulo);

    @Query("FROM Pelicula WHERE disponible = ?1 ORDER BY titulo")
    List<Pelicula> disponibles(@Param("disponible") Boolean disponible);

    @Query("FROM Pelicula ORDER BY titulo")
    List<Pelicula> findAllOrderByTitulo();
}

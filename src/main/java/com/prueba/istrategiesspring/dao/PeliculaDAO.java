package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaDAO extends JpaRepository<Pelicula, Long>, PagingAndSortingRepository<Pelicula, Long> {

    @Query("FROM Pelicula WHERE titulo like CONCAT('%', ?1, '%') GROUP BY titulo")
    List<Pelicula> findByTitulo(@Param("titulo") String titulo);

    Page<Pelicula> findByDisponible(boolean disponible, Pageable pageable);

    @Query("FROM Pelicula ORDER BY titulo")
    List<Pelicula> findAllOrderByTitulo();

    Page<Pelicula> findAll(Pageable pageable);
}

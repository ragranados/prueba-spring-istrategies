package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaDAO extends JpaRepository<Pelicula, Long> {

}

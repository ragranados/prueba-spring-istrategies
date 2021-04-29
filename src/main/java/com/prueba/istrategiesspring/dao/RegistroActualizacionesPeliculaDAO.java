package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.RegistroActualizacionesPelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroActualizacionesPeliculaDAO extends JpaRepository<RegistroActualizacionesPelicula, Long> {

}

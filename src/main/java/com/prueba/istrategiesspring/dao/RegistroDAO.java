package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RegistroDAO extends JpaRepository<Registro, Long> {

    @Query("FROM Registro WHERE tipo = ?1 ORDER BY fecha DESC")
    List<Registro> encontrarPorTipo(@Param("tipo") String tipo);

}

package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionDAO extends JpaRepository<Transaccion, Long> {

    @Query("FROM Transaccion  WHERE usuario.id = ?1 ORDER BY fecha")
    List<Transaccion> findByIdUsuario(@Param("usuario") Long usuario);
}

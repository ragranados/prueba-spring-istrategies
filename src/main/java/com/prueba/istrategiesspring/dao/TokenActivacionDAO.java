package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.TokenActivacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenActivacionDAO extends JpaRepository<TokenActivacion, Long> {

    @Query("FROM TokenActivacion WHERE token = ?1")
    TokenActivacion findByToken(@Param("token") String token);
}

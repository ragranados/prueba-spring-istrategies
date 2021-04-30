package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDAO extends JpaRepository<Compra, Long> {
}

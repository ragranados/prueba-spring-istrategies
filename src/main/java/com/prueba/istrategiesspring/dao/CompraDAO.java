package com.prueba.istrategiesspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDAO extends JpaRepository<CompraDAO, Long> {
}

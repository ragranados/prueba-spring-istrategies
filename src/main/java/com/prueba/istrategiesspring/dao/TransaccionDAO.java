package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionDAO extends JpaRepository<Transaccion, Long> {
}

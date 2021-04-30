package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlquilerDAO extends JpaRepository<Alquiler, Long> {
    
}

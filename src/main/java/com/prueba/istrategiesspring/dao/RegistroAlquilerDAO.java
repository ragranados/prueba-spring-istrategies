package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.RegistroAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RegistroAlquilerDAO extends JpaRepository<RegistroAlquiler, Long> {

}

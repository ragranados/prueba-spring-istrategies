package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.dto.AlquilerDTO;
import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.models.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerDAO extends JpaRepository<Alquiler, Long>, PagingAndSortingRepository<Alquiler, Long> {
    Page<Alquiler> findAll(Pageable pageable);

}

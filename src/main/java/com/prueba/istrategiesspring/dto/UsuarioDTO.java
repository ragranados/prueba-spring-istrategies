package com.prueba.istrategiesspring.dto;

import com.prueba.istrategiesspring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDTO extends JpaRepository<Usuario, Long> { }

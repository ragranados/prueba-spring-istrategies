package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    @Query("FROM Usuario WHERE email = ?1")
    Usuario findByEmail (@Param("email") String email);
}

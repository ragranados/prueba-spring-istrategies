package com.prueba.istrategiesspring.dto;

import com.prueba.istrategiesspring.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDTO extends JpaRepository<Role, Long> {
}

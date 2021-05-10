package com.prueba.istrategiesspring.dto;

import com.prueba.istrategiesspring.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private long id;

    private String email;

    private Role role;

}

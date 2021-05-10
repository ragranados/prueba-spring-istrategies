package com.prueba.istrategiesspring.dto.Response;

import com.prueba.istrategiesspring.dto.UsuarioDTO;
import com.prueba.istrategiesspring.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String jwt;
    private UsuarioDTO usuario;
}

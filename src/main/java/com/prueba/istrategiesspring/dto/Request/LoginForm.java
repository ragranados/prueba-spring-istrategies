package com.prueba.istrategiesspring.dto.Request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    @NotNull(message = "Es un campo requerido")
    @NotBlank(message = "No puede ser una cadena vacía")
    @Email(message = "No tiene formato de email")
    private String email;

    @NotNull(message = "Es un campo requerido")
    @NotBlank(message = "No puede ser una cadena vacia")
    private String password;
}

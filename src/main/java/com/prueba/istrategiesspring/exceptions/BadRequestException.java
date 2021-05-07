package com.prueba.istrategiesspring.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
public class BadRequestException extends RuntimeException{

    private String message;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}

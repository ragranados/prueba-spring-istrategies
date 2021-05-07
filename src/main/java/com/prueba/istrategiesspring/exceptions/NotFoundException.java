package com.prueba.istrategiesspring.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

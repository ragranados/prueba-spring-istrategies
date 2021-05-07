package com.prueba.istrategiesspring.exceptions;

import lombok.Data;

@Data
public class OutOfStockException extends RuntimeException{

    private String message;

    public OutOfStockException() {
    }

    public OutOfStockException(String message) {
        super(message);
        this.message = message;
    }
}

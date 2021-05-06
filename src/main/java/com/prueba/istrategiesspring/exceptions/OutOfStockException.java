package com.prueba.istrategiesspring.exceptions;

public class OutOfStockException extends RuntimeException{

    public OutOfStockException() {
    }

    public OutOfStockException(String message) {
        super(message);
    }
}

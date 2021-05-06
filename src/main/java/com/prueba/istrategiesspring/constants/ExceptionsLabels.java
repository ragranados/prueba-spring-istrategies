package com.prueba.istrategiesspring.constants;

public enum ExceptionsLabels {

    NOT_FOUND("No se encontraron resultados");

    public final String frase;

    ExceptionsLabels(String frase) {
        this.frase = frase;
    }
}

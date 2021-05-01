package com.prueba.istrategiesspring.requests;

import java.util.List;

public class DevolucionRequest {

    private List<Long> alquiler;

    public List<Long> getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(List<Long> alquiler) {
        this.alquiler = alquiler;
    }
}

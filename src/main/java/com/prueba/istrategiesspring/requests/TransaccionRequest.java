package com.prueba.istrategiesspring.requests;

import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.models.Pelicula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransaccionRequest {

    private List<Long> compras;

    private List<Long> alquiler;

    public List<Long> getCompras() {
        return compras;
    }

    public void setCompras(List<Long> compras) {
        this.compras = compras;
    }

    public List<Long> getAlquiler() { 
        return alquiler;
    }

    public void setAlquiler(List<Long> alquiler) {
        this.alquiler = alquiler;
    }
}

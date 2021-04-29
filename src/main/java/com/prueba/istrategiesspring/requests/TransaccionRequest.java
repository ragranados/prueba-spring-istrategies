package com.prueba.istrategiesspring.requests;

import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.models.Pelicula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransaccionRequest {

    private List<Long> compras;

    //private List<Alquiler> alquiler;

    public List<Long> getCompras() {
        return compras;
    }

    public void setCompras(List<Long> compras) {
        this.compras = compras;
    }
}

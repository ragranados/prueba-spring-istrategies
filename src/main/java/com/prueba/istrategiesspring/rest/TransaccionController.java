package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Compra;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.Transaccion;
import com.prueba.istrategiesspring.requests.TransaccionRequest;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.PeliculaService;
import com.prueba.istrategiesspring.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    @RequestMapping("/guardar")
    public ResponseEntity guardar(@RequestBody TransaccionRequest transaccionRequest){

        try {
            ServiceResponse serviceResponse = transaccionService.crearTransaccion(new Transaccion());


            Transaccion nTransaccion = (Transaccion) serviceResponse.getData();
            Compra compra = new Compra();
            compra.setTransaccion(nTransaccion);
            List<Pelicula> peliculaList =(List<Pelicula>) peliculaService.obtenerMultiplesPorId(transaccionRequest.getCompras()).getData();
            compra.setPeliculas(peliculaList);
            nTransaccion.setCompra(compra);
            return ResponseEntity.ok(transaccionRequest.getCompras());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

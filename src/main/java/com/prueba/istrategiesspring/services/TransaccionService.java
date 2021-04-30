package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.CompraDAO;
import com.prueba.istrategiesspring.dao.PeliculaDAO;
import com.prueba.istrategiesspring.dao.TransaccionDAO;
import com.prueba.istrategiesspring.models.Compra;
import com.prueba.istrategiesspring.models.Transaccion;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class TransaccionService {

    @Autowired
    private TransaccionDAO transaccionDAO;

    @Autowired
    private CompraDAO compraDAO;

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Transactional
    public ServiceResponse crearTransaccion(Usuario usuario, List<Long> compras){
        try {

            Transaccion nTrsansaccion = new Transaccion();
            nTrsansaccion.setUsuario(usuario);
            Transaccion inserted = transaccionDAO.save(nTrsansaccion);

            if(inserted == null){
                return new ServiceResponse(false, "No se ha podido crear la transaccion", null);
            }

            Compra nCompra = new Compra();
            nCompra.setFechaCompra(LocalDate.now());
            nCompra.setTransaccion(nTrsansaccion);
            nCompra.setPeliculas(peliculaDAO.findAllById(compras));

            compraDAO.save(nCompra);

            return new ServiceResponse(true, "Transaccion realizada con exito", nTrsansaccion);
        } catch (Exception e) {
            throw e;
        }

    }

    public ServiceResponse obtenerTransaccionesPorIdUsuario(Long id){
        try {
            List<Transaccion> transacciones = transaccionDAO.findByIdUsuario(id);

            if(transacciones.isEmpty()){
                return new ServiceResponse(false, "No se encontraron resultados", null);
            }

            return new ServiceResponse(true, "Ok", transacciones);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

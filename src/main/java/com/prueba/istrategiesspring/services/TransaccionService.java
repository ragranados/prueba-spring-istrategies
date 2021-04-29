package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.TransaccionDAO;
import com.prueba.istrategiesspring.models.Transaccion;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransaccionService {

    @Autowired
    private TransaccionDAO transaccionDAO;

    @Transactional
    public ServiceResponse crearTransaccion(Transaccion transaccion){
        try {
            Transaccion nTrsansaccion = transaccionDAO.save(transaccion);
            System.out.println(nTrsansaccion.getId());
            //throw new RuntimeException();
            if(nTrsansaccion == null){
                return new ServiceResponse(false, "No se ha podido crear la transaccion", null);
            }

            return new ServiceResponse(true, "Transaccion realizada con exito", nTrsansaccion);
        } catch (Exception e) {
            throw e;
            //return new ServiceResponse(false, e.getMessage(), null);
        }

    }
}

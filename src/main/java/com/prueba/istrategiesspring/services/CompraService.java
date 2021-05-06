package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.RegistroDAO;
import com.prueba.istrategiesspring.models.Registro;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private RegistroDAO registroDAO;

    public ServiceResponse registroCompra() {

        List<Registro> registroList = registroDAO.encontrarPorTipo("compra");

        if (registroList.isEmpty()) {
            return new ServiceResponse(false, "No se encontraron resultados", null);
        }

        return new ServiceResponse(true, "Ok", registroList);

    }

}

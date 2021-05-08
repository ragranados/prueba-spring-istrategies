package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.RegistroDAO;
import com.prueba.istrategiesspring.models.Registro;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private RegistroDAO registroDAO;

    public ServiceResponse registroCompra(Pageable pageable) {

        Page<Registro> registroList = registroDAO.findAllByTipoOrderByFecha("compra", pageable);

        if (registroList.isEmpty()) {
            return new ServiceResponse(false, "No se encontraron resultados", null);
        }

        return new ServiceResponse(true, "Ok", registroList);

    }

}

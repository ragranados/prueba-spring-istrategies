package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.constants.ExceptionsLabels;
import com.prueba.istrategiesspring.dao.AlquilerDAO;
import com.prueba.istrategiesspring.dao.PeliculaDAO;
import com.prueba.istrategiesspring.dao.RegistroDAO;
import com.prueba.istrategiesspring.exceptions.NotFoundException;
import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.Registro;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AlquierService {

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Autowired
    private AlquilerDAO alquilerDAO;

    @Autowired
    private RegistroDAO registroDAO;

    private Float calcularRecargo(List<Alquiler> alquiler) {
        Float montoPorDia = 3.0f;
        Float recargo = 0f;

        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < alquiler.size(); i++) {
            LocalDate devolucion = alquiler.get(i).getFechaDevolucion();

            if (ChronoUnit.DAYS.between(devolucion, hoy) > 0) {
                recargo += ChronoUnit.DAYS.between(devolucion, hoy) * montoPorDia;
                System.out.println("Dias " + ChronoUnit.DAYS.between(devolucion, hoy) + " Fecha devolucion: " + alquiler.get(i).getFechaDevolucion());
            }

        }

        return recargo;
    }

    public ServiceResponse devolucionPelicula(List<Alquiler> alquiler) {
        try {
            for (int i = 0; i < alquiler.size(); i++) {
                if (!alquiler.get(i).isDevuelto()) {
                    alquiler.get(i).setDevuelto(true);

                    Optional<Pelicula> pelicula = peliculaDAO.findById(alquiler.get(i).getPelicula().getId());

                    pelicula.get().setStockAlquiler(pelicula.get().getStockAlquiler() + 1);

                    peliculaDAO.save(pelicula.get());
                    alquilerDAO.save(alquiler.get(i));
                }
            }

            Float recargo = calcularRecargo(alquiler);

            return new ServiceResponse(true, "Ok", recargo);
        } catch (Exception e) {
            throw e;
        }
    }

    public ServiceResponse encontrarMultiples(List<Long> alquier) {

        List<Alquiler> alquilerList = alquilerDAO.findAllById(alquier);

        if (alquilerList.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", alquilerList);
    }

    public ServiceResponse registroAlquiler(Pageable pageable) {

        Page<Registro> registroList = registroDAO.findAllByTipoOrderByFecha("alquiler", pageable);

        if (registroList.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", registroList);
    }
}

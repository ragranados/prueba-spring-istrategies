package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.AlquilerDAO;
import com.prueba.istrategiesspring.dao.PeliculaDAO;
import com.prueba.istrategiesspring.dao.RegistroAlquilerDAO;
import com.prueba.istrategiesspring.dto.AlquilerDTO;
import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.RegistroAlquiler;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AlquierService {

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Autowired
    private AlquilerDAO alquilerDAO;

    @Autowired
    private RegistroAlquilerDAO registroAlquilerDAO;

    private Float calcularRecargo(List<Alquiler> alquiler) {
        Float montoPorDia = 3.0f;
        Float recargo = 0f;

        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < alquiler.size(); i++) {
            LocalDate devolucion = alquiler.get(i).getFechaDevolucion();

            if(ChronoUnit.DAYS.between(devolucion, hoy) > 0){
                recargo += ChronoUnit.DAYS.between(devolucion, hoy) * montoPorDia;
                System.out.println("Dias " + ChronoUnit.DAYS.between(devolucion, hoy) + " Fecha devolucion: " + alquiler.get(i).getFechaDevolucion());
            }

        }

        return recargo;
    }

    @Transactional
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
            //return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse encontrarMultiples(List<Long> alquier) {
        try {
            List<Alquiler> alquilerList = alquilerDAO.findAllById(alquier);

            if (alquilerList.isEmpty()) {
                return new ServiceResponse(false, "No se obtuvieron resultados", null);
            }

            return new ServiceResponse(true, "Ok", alquilerList);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse registroAlquiler(){
        try {
            List<RegistroAlquiler> registroAlquilerList = registroAlquilerDAO.findAll();

            if(registroAlquilerList.isEmpty()){
                return new ServiceResponse(false, "No se encontraron resultados", null);
            }

            return new ServiceResponse(true, "Ok", registroAlquilerList);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

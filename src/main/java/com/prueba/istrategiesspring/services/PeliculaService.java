package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.PeliculaDAO;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaDAO peliculaDAO;

    public ServiceResponse crearPelicula(Pelicula pelicula){
        try{
            Pelicula nPelicula = peliculaDAO.save(pelicula);

            if(pelicula == null){
                return new ServiceResponse(false, "No se ha podido ingresar la pelicula", null);
            }

            return new ServiceResponse(true, "Ok", nPelicula);
        }catch (Exception e){
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse obtenerPeliculas(){
        try {
            List<Pelicula> peliculas = peliculaDAO.findAll();

            if(peliculas.isEmpty()){
                return new ServiceResponse(false, "No se obtuvieron resultados", peliculas);
            }

            return new ServiceResponse(true, "Ok", peliculas);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse obtenerPeliculaPorId(Long id){
        try {
            Optional<Pelicula> pelicula = peliculaDAO.findById(id);

            if(!pelicula.isPresent()){
                return new ServiceResponse(false, "No se obtuvieron resultados", null);
            }

            return new ServiceResponse(true, "Ok", pelicula);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    public ServiceResponse obtenerPeliculaPorNombre(String titulo){
        try {
            List<Pelicula> peliculas = peliculaDAO.findByTitulo(titulo);

            if(peliculas.isEmpty()){
                return new ServiceResponse(false, "No se obtuveron resultados", peliculas);
            }

            return new ServiceResponse(true, "Ok", peliculas);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

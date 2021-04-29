package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.dao.PeliculaDAO;
import com.prueba.istrategiesspring.dao.RegistroActualizacionesPeliculaDAO;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.RegistroActualizacionesPelicula;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Autowired
    private RegistroActualizacionesPeliculaDAO registroActualizacionesPeliculaDAO;

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

    public ServiceResponse obtenerPeliculasDisponibles(){
        try {
            List<Pelicula> peliculas = peliculaDAO.disponibles();

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

            return new ServiceResponse(true, "Ok", pelicula.get());
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

    public ServiceResponse cambiarDisponibilidad (Pelicula nPelicula,boolean disponibilidad){
        nPelicula.setDisponible(disponibilidad);

        try {
            Pelicula pelicula = peliculaDAO.save(nPelicula);

            return new ServiceResponse(true, "Disponibilidad actualizada con exito", pelicula);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }

    @Transactional
    public ServiceResponse actualizarPelicula (Pelicula nPelicula){
        try {
            Optional<Pelicula> pelicula = peliculaDAO.findById(nPelicula.getId());

            if(!pelicula.isPresent()){
                return new ServiceResponse(false, "Pelicula no encontrada", null);
            }

            if(!nPelicula.getPrecioCompra().equals(pelicula.get().getPrecioCompra())  || !nPelicula.getPrecioAlquiler().equals(pelicula.get().getPrecioAlquiler()) || !nPelicula.getTitulo().equals(pelicula.get().getTitulo())){
                RegistroActualizacionesPelicula registroActualizacionesPelicula = new RegistroActualizacionesPelicula();

                registroActualizacionesPelicula.setPrecioAlquilerAnterior(pelicula.get().getPrecioAlquiler());
                registroActualizacionesPelicula.setPrecioCompraAnterior(pelicula.get().getPrecioCompra());
                registroActualizacionesPelicula.setTituloAnterior(pelicula.get().getTitulo());

                registroActualizacionesPelicula.setPelicula(pelicula.get());
                registroActualizacionesPelicula.setPrecioAlquiler(nPelicula.getPrecioAlquiler());
                registroActualizacionesPelicula.setTitulo(nPelicula.getTitulo());
                registroActualizacionesPelicula.setPrecioCompra(nPelicula.getPrecioCompra());

                registroActualizacionesPeliculaDAO.save(registroActualizacionesPelicula);
            }

            peliculaDAO.save(nPelicula);

            return new ServiceResponse(true, "Campos actualizados con exito", nPelicula);
        } catch (Exception e) {
            return new ServiceResponse(false, e.getMessage(), null);
        }
    }
}

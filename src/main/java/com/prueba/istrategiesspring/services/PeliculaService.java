package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.constants.ExceptionsLabels;
import com.prueba.istrategiesspring.dao.PeliculaDAO;
import com.prueba.istrategiesspring.dao.RegistroActualizacionesPeliculaDAO;
import com.prueba.istrategiesspring.dao.UsuarioDAO;
import com.prueba.istrategiesspring.dto.PeliculaDTO;
import com.prueba.istrategiesspring.exceptions.BadRequestException;
import com.prueba.istrategiesspring.exceptions.NotFoundException;
import com.prueba.istrategiesspring.models.Pelicula;
import com.prueba.istrategiesspring.models.RegistroActualizacionesPelicula;
import com.prueba.istrategiesspring.models.Usuario;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/*class LikesComparator implements Comparator<Pelicula>{
    @Override
    public int compare(Pelicula o1, Pelicula o2) {
        return o2.getMeGustas().size() - o1.getMeGustas().size();
    }
}*/

@Service
@Transactional(rollbackFor = Exception.class)
public class PeliculaService {

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RegistroActualizacionesPeliculaDAO registroActualizacionesPeliculaDAO;

    private ModelMapper modelMapper = new ModelMapper();

    public ServiceResponse crearPelicula(PeliculaDTO pelicula) {

        Pelicula mappedPelicula = modelMapper.map(peliculaDAO, Pelicula.class);

        Pelicula nPelicula = peliculaDAO.save(mappedPelicula);

        if (pelicula == null) {
            throw new BadRequestException("No se ha podido crear la pelicula");
        }

        return new ServiceResponse(true, "Ok", nPelicula);
    }

    public ServiceResponse obtenerPeliculas(Pageable pageable) {

        Page<Pelicula> peliculas = peliculaDAO.findAll(pageable);

        if (peliculas.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", peliculas);

    }

    public ServiceResponse obtenerPeliculasFiltro(boolean disponible, Pageable pageable) {

        Page<Pelicula> peliculas = peliculaDAO.findByDisponible(disponible, pageable);

        if (peliculas.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", peliculas);

    }

    public ServiceResponse obtenerPeliculaPorId(Long id) {

        Optional<Pelicula> pelicula = peliculaDAO.findById(id);

        if (!pelicula.isPresent()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", pelicula.get());
    }

    public ServiceResponse obtenerMultiplesPorId(List<Long> ids) {

        List<Pelicula> peliculas = peliculaDAO.findAllById(ids);

        if (peliculas.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Resultados", peliculas);


    }

    public ServiceResponse obtenerPeliculaPorNombre(String titulo) {

        List<Pelicula> peliculas = peliculaDAO.findByTitulo(titulo);

        if (peliculas.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", peliculas);

    }

    public ServiceResponse cambiarDisponibilidad(Pelicula nPelicula, boolean disponibilidad) {
        nPelicula.setDisponible(disponibilidad);

        try {
            Pelicula pelicula = peliculaDAO.save(nPelicula);

            return new ServiceResponse(true, "Disponibilidad actualizada con exito", pelicula);
        } catch (Exception e) {
            throw e;
        }
    }

    public ServiceResponse addLike(Pelicula pelicula, Usuario usuario) {

        if (usuario.getPeliculasGustadas().contains(pelicula)) {
            return new ServiceResponse(true, "Ya se le ha dado like a esta pelicula", pelicula);
        }

        usuario.getPeliculasGustadas().add(pelicula);

        usuarioDAO.save(usuario);

        if (pelicula == null) {
            throw new BadRequestException("No se ha poddo guardar el like");
        }

        return new ServiceResponse(true, "Ok", pelicula);

    }

    public ServiceResponse actualizarPelicula(Pelicula nPelicula) {

        Optional<Pelicula> pelicula = peliculaDAO.findById(nPelicula.getId());

        if (!pelicula.isPresent()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        if (!nPelicula.getPrecioCompra().equals(pelicula.get().getPrecioCompra()) || !nPelicula.getPrecioAlquiler().equals(pelicula.get().getPrecioAlquiler()) || !nPelicula.getTitulo().equals(pelicula.get().getTitulo())) {
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

    }
}

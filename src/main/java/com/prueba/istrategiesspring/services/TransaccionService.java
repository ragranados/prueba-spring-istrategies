package com.prueba.istrategiesspring.services;

import com.prueba.istrategiesspring.constants.ExceptionsLabels;
import com.prueba.istrategiesspring.dao.*;
import com.prueba.istrategiesspring.exceptions.NotFoundException;
import com.prueba.istrategiesspring.exceptions.OutOfStockException;
import com.prueba.istrategiesspring.models.*;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackFor = Exception.class)
public class TransaccionService {

    @Autowired
    private TransaccionDAO transaccionDAO;

    @Autowired
    private CompraDAO compraDAO;

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Autowired
    private AlquilerDAO alquilerDAO;

    @Autowired
    private RegistroDAO registroDAO;

    private void registroAlquiler(LocalDate fecha, List<Long> alquiler, List<Long> compra, Transaccion transaccion, Usuario usuario) {
        if (!alquiler.isEmpty()) {
            Registro registro = new Registro();

            registro.setTransaccion(transaccion);
            registro.setFecha(fecha);
            registro.setUsuario(usuario);
            registro.setNumeroPeliculas(alquiler.size());
            registro.setTipo("alquiler");

            registroDAO.save(registro);
        }

        if (!compra.isEmpty()) {
            Registro registro1 = new Registro();

            registro1.setTransaccion(transaccion);
            registro1.setFecha(fecha);
            registro1.setUsuario(usuario);
            registro1.setNumeroPeliculas(compra.size());
            registro1.setTipo("compra");

            registroDAO.save(registro1);
        }

    }

    private BigDecimal calcularTotal(List<Pelicula> compras, List<Pelicula> alquiler) {

        BigDecimal total = new BigDecimal("0");

        for (int i = 0; i < compras.size(); i++) {
            total = total.add(compras.get(i).getPrecioCompra());
        }

        for (int i = 0; i < alquiler.size(); i++) {
            total = total.add(alquiler.get(i).getPrecioAlquiler());

        }

        return total;
    }

    private void validarStock(List<Long> compras, List<Long> alquiler) {
        for (int i = 0; i < compras.size(); i++) {
            Optional<Pelicula> pelicula = peliculaDAO.findById(compras.get(i));

            if (pelicula.get().getStockCompra() < 1 || !pelicula.get().isDisponible()) {
                throw new OutOfStockException("La pelicula " + pelicula.get().getTitulo() + " se encuentra agotada.");
            }

            pelicula.get().setStockCompra(pelicula.get().getStockCompra() - 1);
            peliculaDAO.save(pelicula.get());
        }

        for (int i = 0; i < alquiler.size(); i++) {
            Optional<Pelicula> pelicula = peliculaDAO.findById(alquiler.get(i));

            if (pelicula.get().getStockAlquiler() < 1 || !pelicula.get().isDisponible()) {
                throw new OutOfStockException("La pelicula " + pelicula.get().getTitulo() + " se encuentra agotada.");
            }

            pelicula.get().setStockAlquiler(pelicula.get().getStockAlquiler() - 1);
            peliculaDAO.save(pelicula.get());

        }
    }

    public ServiceResponse crearTransaccion(Usuario usuario, List<Long> compras, List<Long> alquiler) {

        validarStock(compras, alquiler);

        List<Pelicula> peliculasCompra = new ArrayList<>();
        List<Pelicula> peliculasAlquiler = new ArrayList<>();

        if (!compras.isEmpty()) peliculasCompra = peliculaDAO.findAllById(compras);
        if (!alquiler.isEmpty()) peliculasAlquiler = peliculaDAO.findAllById(alquiler);

        BigDecimal total = calcularTotal(peliculasCompra, peliculasAlquiler);
        LocalDate date = LocalDate.now();

        Transaccion nTrsansaccion = new Transaccion();
        nTrsansaccion.setUsuario(usuario);
        nTrsansaccion.setFecha(date);
        nTrsansaccion.setPrecioTotal(total);

        Transaccion inserted = transaccionDAO.save(nTrsansaccion);

        if (inserted == null) {
            throw new RuntimeException("No se ha podido crear la transaccion");
        }

        List<Alquiler> nAlquilerArray = new ArrayList<>();

        for (int i = 0; i < alquiler.size(); i++) {
            Alquiler nAlquiler = new Alquiler();

            nAlquiler.setFechaAlquiler(date);
            nAlquiler.setTransaccion(nTrsansaccion);
            nAlquiler.setPelicula(peliculasAlquiler.get(i));
            nAlquiler.setFechaDevolucion(date.plusDays(2));

            nAlquilerArray.add(nAlquiler);
        }

        if (!compras.isEmpty()) {
            Compra nCompra = new Compra();
            nCompra.setFechaCompra(date);
            nCompra.setTransaccion(nTrsansaccion);
            nCompra.setPeliculas(peliculaDAO.findAllById(compras));

            compraDAO.save(nCompra);
        }
        registroAlquiler(date, alquiler, compras, nTrsansaccion, usuario);
        alquilerDAO.saveAll(nAlquilerArray);

        return new ServiceResponse(true, "Transaccion realizada con exito", nTrsansaccion);

    }

    public ServiceResponse obtenerTransaccionesPorIdUsuario(Long id) {

        List<Transaccion> transacciones = transaccionDAO.findByIdUsuario(id);

        if (transacciones.isEmpty()) {
            throw new NotFoundException(ExceptionsLabels.NOT_FOUND.frase);
        }

        return new ServiceResponse(true, "Ok", transacciones);
    }


}

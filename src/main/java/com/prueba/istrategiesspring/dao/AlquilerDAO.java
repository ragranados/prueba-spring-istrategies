package com.prueba.istrategiesspring.dao;

import com.prueba.istrategiesspring.dto.AlquilerDTO;
import com.prueba.istrategiesspring.models.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerDAO extends JpaRepository<Alquiler, Long> {

    /*@Query(value = "SELECT new com.prueba.istrategiesspring.dto.AlquilerDTO(Transaccion.id ,Transaccion.usuario.id ,Alquiler.fechaAlquiler, count(Alquiler.pelicula))" +
            "FROM Transaccion " +
            "INNER JOIN Alquiler ON Transaccion.id = Alquiler.transaccion.id " +
            "INNER JOIN Pelicula ON Alquiler.pelicula.id = Pelicula.id " +
            "GROUP BY Transaccion.id")
    List<AlquilerDTO> registroAlquiler();*/

    /*@Query(value =
            "SELECT transaccion.id as \"transaccion\" ,transaccion.usuario_id as \"usuario\",alquiler.fecha_alquiler as \"fecha\", count(alquiler.pelicula_id) as \"numeroPeliculas\"\n" +
            "FROM transaccion \n" +
            "\tINNER JOIN alquiler ON transaccion.id = alquiler.transaccion_id\n" +
            "    INNER JOIN pelicula ON alquiler.pelicula_id = pelicula.id \n" +
            "    GROUP BY transaccion.id\n" +
            "    ORDER BY alquiler.fecha_alquiler", nativeQuery = true)
    List<Object> registroAlquiler();*/
}

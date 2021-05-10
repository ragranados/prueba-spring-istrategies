package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.dto.Request.DevolucionRequest;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.AlquierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/alquiler")
public class AlquilerController {

    @Autowired
    private AlquierService alquierService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/devolver")
    public ResponseEntity devolverAlquiler(@RequestBody DevolucionRequest devolucionRequest) {

        List<Alquiler> alquilerList = (List<Alquiler>) alquierService.encontrarMultiples(devolucionRequest.getAlquiler()).getData();

        ServiceResponse serviceResponse = alquierService.devolucionPelicula(alquilerList);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body("Error");
        }

        HashMap<String, Float> map = new HashMap<>();
        map.put("recargo", (Float) serviceResponse.getData());
        return ResponseEntity.ok(map);

    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/registro")
    public ResponseEntity registroAlquiler(@RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, size);

        ServiceResponse serviceResponse = alquierService.registroAlquiler(pageable);

        if (!serviceResponse.getStatus()) {
            return ResponseEntity.status(400).body(serviceResponse.getMessage());
        }

        return ResponseEntity.ok(serviceResponse.getData());
    }
}

package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.requests.DevolucionRequest;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.AlquierService;
import com.prueba.istrategiesspring.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            List<Alquiler> alquilerList = (List<Alquiler>) alquierService.encontrarMultiples(devolucionRequest.getAlquiler()).getData();

            ServiceResponse serviceResponse = alquierService.devolucionPelicula(alquilerList);

            if (!serviceResponse.getStatus()) {
                return ResponseEntity.status(400).body("Error");
            }

            HashMap<String, Float> map= new HashMap<>();
            map.put("recargo",(Float) serviceResponse.getData());
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage()); // 7 9
        }
    }
}

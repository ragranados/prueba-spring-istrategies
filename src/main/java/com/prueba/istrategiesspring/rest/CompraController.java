package com.prueba.istrategiesspring.rest;

import com.prueba.istrategiesspring.models.Alquiler;
import com.prueba.istrategiesspring.requests.DevolucionRequest;
import com.prueba.istrategiesspring.responses.ServiceResponse;
import com.prueba.istrategiesspring.services.AlquierService;
import com.prueba.istrategiesspring.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/registro")
    public ResponseEntity registroAlquiler(@RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "0") int page) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            ServiceResponse serviceResponse = compraService.registroCompra(pageable);

            if (!serviceResponse.getStatus()) {
                return ResponseEntity.status(400).body(serviceResponse.getMessage());
            }

            return ResponseEntity.ok(serviceResponse.getData());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

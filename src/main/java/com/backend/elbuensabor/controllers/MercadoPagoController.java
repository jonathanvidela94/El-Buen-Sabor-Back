package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.services.MercadoPagoService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/mercadopago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService service;

    @PostMapping("/generatePreference/{totalOrder}")
    public ResponseEntity<Preference> generatePreference(@PathVariable Double totalOrder) throws MPException, MPApiException {
        Preference preference = service.generatePreference(totalOrder);
        return new ResponseEntity<>(preference, HttpStatus.OK);
    }

}

package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Orders;
import com.backend.elbuensabor.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/orders")
public class OrdersController extends GenericControllerImpl<Orders, OrdersDTO> {

    @Autowired
    private OrdersService service;

    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @Override
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody OrdersDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.saveOrder(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

}

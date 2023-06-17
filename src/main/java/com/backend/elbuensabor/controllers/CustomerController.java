package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.CustomerDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Customer;
import com.backend.elbuensabor.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/customers")
public class CustomerController extends GenericControllerImpl<Customer, CustomerDTO> {

    @Autowired
    private CustomerService service;
    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @GetMapping("/different-role/{roleId}")
    public ResponseEntity<?> findAllCustomersWithDifferentRoleId(@PathVariable Long roleId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllCustomersWithDifferentRoleId(roleId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_MESSAGE);
        }
    }

}

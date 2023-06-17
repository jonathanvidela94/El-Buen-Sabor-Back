package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.UserDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.User;
import com.backend.elbuensabor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/user")
public class UserController extends GenericControllerImpl<User, UserDTO> {

    @Autowired
    private UserService service;
    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @PutMapping("/{id}/block")
    public ResponseEntity<?> bloquearDesbloquearEmpleado(@PathVariable Long id, @RequestParam boolean blocked) {
        try {
            User user = service.bloquearDesbloquearEmpleado(id, blocked);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }
}


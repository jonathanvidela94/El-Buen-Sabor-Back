package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.RoleDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Role;
import com.backend.elbuensabor.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/roles")
public class RoleController extends GenericControllerImpl<Role, RoleDTO> {

    @Autowired
    private RoleService service;
}

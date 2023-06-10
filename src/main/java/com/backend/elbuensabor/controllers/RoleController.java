package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Role;
import com.backend.elbuensabor.services.impl.RoleServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/roles")
public class RoleController extends GenericControllerImpl<Role, RoleServiceImpl> {
    //Get All @GET
    //http://localhost:8080/api/v1/roles

//   @PreAuthorize("hasAuthority('all')")

    //Get One @GET
    //http://localhost:8080/api/v1/roles/id

    //Create @POST
    //http://localhost:8080/api/v1/roles + JSON

    //Update @PUT
    //http://localhost:8080/api/v1/roles/id + JSON

    //Delete @DELETE
    //http://localhost:8080/api/v1/roles/id

    //Implementacion de los metodos de cada clase.
}

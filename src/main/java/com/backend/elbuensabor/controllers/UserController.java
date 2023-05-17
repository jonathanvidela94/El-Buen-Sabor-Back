package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.User;
import com.backend.elbuensabor.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/user")
public class UserController extends GenericControllerImpl<User, UserServiceImpl> {

    //Get All @GET
    //http://localhost:8080/api/v1/user

    //Get One @GET
    //http://localhost:8080/api/v1/user/id

    //Create @POST
    //http://localhost:8080/api/v1/user + JSON

    //Update @PUT
    //http://localhost:8080/api/v1/user/id + JSON

    //Delete @DELETE
    //http://localhost:8080/api/v1/user/id

}


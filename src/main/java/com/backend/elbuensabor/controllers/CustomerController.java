//package com.backend.elbuensabor.controllers;
//
//import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
//import com.backend.elbuensabor.entities.Customer;
//import com.backend.elbuensabor.services.impl.CustomerServiceImpl;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping(path = "api/v1/customers")
//public class CustomerController extends GenericControllerImpl<Customer, CustomerServiceImpl> {
//
//    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";
//
//
//    //Get All @GET
//    //http://localhost:8080/api/v1/customers
//
//    //Get One @GET
//    //http://localhost:8080/api/v1/customers/id
//
//    //Create @POST
//    //http://localhost:8080/api/v1/customers + JSON
//
//    //Update @PUT
//    //http://localhost:8080/api/v1/customers/id + JSON
//
//    //Delete @DELETE
//    //http://localhost:8080/api/v1/customers/id
//
//    @GetMapping("/different-role/{roleId}")
//    public ResponseEntity<?> findAllCustomersWithDifferentRoleId(@PathVariable Long roleId) {
//        try {
//            List<Customer> customers = service.findAllCustomersWithDifferentRoleId(roleId);
//            return ResponseEntity.status(HttpStatus.OK).body(customers);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_MESSAGE);
//        }
//    }
//
//}

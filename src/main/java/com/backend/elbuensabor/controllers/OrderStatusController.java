package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.OrderStatusDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.OrderStatus;
import com.backend.elbuensabor.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/order-status")
public class OrderStatusController extends GenericControllerImpl<OrderStatus, OrderStatusDTO> {
    @Autowired
    private OrderStatusService service;
}

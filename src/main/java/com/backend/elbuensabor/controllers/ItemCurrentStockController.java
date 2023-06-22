package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemCurrentStockDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.ItemCurrentStock;
import com.backend.elbuensabor.services.ItemCurrentStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/itemCurrentStock")
public class ItemCurrentStockController extends GenericControllerImpl <ItemCurrentStock, ItemCurrentStockDTO> {
    @Autowired
    ItemCurrentStockService service;
}

package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemCostPriceDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.ItemCostPrice;
import com.backend.elbuensabor.services.ItemCostPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/itemCostPrice")
public class ItemCostPriceController extends GenericControllerImpl <ItemCostPrice, ItemCostPriceDTO> {
    @Autowired
    ItemCostPriceService service;
}

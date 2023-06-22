package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemMeasurementUnitDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.ItemMeasurementUnit;
import com.backend.elbuensabor.services.ItemMeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/measurementUnitItems")
public class ItemMeasurementUnitController extends GenericControllerImpl<ItemMeasurementUnit, ItemMeasurementUnitDTO> {
    @Autowired
    private ItemMeasurementUnitService service;
}

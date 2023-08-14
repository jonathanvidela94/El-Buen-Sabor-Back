package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.MeasurementUnitDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.MeasurementUnit;
import com.backend.elbuensabor.services.MeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/measurementUnits")
public class MeasurementUnitController extends GenericControllerImpl <MeasurementUnit, MeasurementUnitDTO> {

    @Autowired
    private MeasurementUnitService service;

}

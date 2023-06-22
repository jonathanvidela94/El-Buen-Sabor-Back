package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemStockConfigDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.ItemStockConfig;
import com.backend.elbuensabor.services.ItemStockConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/itemStockConfig")
public class ItemStockConfigController extends GenericControllerImpl <ItemStockConfig, ItemStockConfigDTO> {
    @Autowired
    ItemStockConfigService service;
}

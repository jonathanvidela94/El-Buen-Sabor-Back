package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemImageDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.ItemImage;
import com.backend.elbuensabor.services.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/images")
public class ItemImageController extends GenericControllerImpl<ItemImage, ItemImageDTO> {
    @Autowired
    private ItemImageService service;
}

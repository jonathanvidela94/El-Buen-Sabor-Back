package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.RecipeDetailDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.RecipeDetail;
import com.backend.elbuensabor.services.RecipeDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeDetailController extends GenericControllerImpl<RecipeDetail, RecipeDetailDTO> {

    @Autowired
    RecipeDetailService service;
}

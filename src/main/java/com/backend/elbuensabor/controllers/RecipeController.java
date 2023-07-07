package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.RecipeDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Recipe;
import com.backend.elbuensabor.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeController extends GenericControllerImpl<Recipe, RecipeDTO> {

    @Autowired
    RecipeService service;
}

package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.RecipeDTO;
import com.backend.elbuensabor.entities.Recipe;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.RecipeMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.RecipeRepository;
import com.backend.elbuensabor.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl extends GenericServiceImpl<Recipe, RecipeDTO, Long> implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper = RecipeMapper.getInstance();

    public RecipeServiceImpl(GenericRepository<Recipe, Long> genericRepository, GenericMapper<Recipe, RecipeDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

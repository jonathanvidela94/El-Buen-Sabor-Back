package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.RecipeDetailDTO;
import com.backend.elbuensabor.entities.RecipeDetail;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.RecipeDetailMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.RecipeDetailRepository;
import com.backend.elbuensabor.services.RecipeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeDetailServiceImpl extends GenericServiceImpl<RecipeDetail, RecipeDetailDTO, Long> implements RecipeDetailService {
    @Autowired
    private RecipeDetailRepository recipeDetailRepository;

    private final RecipeDetailMapper recipeDetailMapper = RecipeDetailMapper.getInstance();

    public RecipeDetailServiceImpl(GenericRepository<RecipeDetail, Long> genericRepository, GenericMapper<RecipeDetail, RecipeDetailDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

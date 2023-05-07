package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.RecipeDetail;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.RecipeDetailRepository;
import com.backend.elbuensabor.services.RecipeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeDetailServiceImpl extends GenericServiceImpl<RecipeDetail,Long> implements RecipeDetailService {
    @Autowired
    private RecipeDetailRepository recipeDetailRepository;

    public RecipeDetailServiceImpl(GenericRepository<RecipeDetail, Long> genericRepository) {
        super(genericRepository);
    }
}

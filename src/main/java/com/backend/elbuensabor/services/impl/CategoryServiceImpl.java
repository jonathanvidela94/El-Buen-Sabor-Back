package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.repositories.CategoryRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category,Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(GenericRepository<Category, Long> genericRepository) {
        super(genericRepository);
    }

}

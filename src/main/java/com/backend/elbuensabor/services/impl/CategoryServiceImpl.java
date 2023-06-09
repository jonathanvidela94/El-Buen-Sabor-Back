package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.repositories.CategoryRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category,Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(GenericRepository<Category, Long> genericRepository) {
        super(genericRepository);
    }

    @Transactional
    @Override
    public Category lockUnlockCategory(Long id, boolean blocked) throws Exception {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
            category.setIsBanned(blocked);
            return categoryRepository.save(category);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

}

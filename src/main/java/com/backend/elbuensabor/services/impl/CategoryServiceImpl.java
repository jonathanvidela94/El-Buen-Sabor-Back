package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.mappers.CategoryMapper;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.repositories.CategoryRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryDTO, Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper = CategoryMapper.getInstance();
    public CategoryServiceImpl(GenericRepository<Category, Long> genericRepository, GenericMapper<Category, CategoryDTO> genericMapper) {
        super(genericRepository, genericMapper);
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

package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;

public interface CategoryService extends GenericService<Category, CategoryDTO, Long>{
    public Category lockUnlockCategory(Long id, boolean blocked) throws Exception;
}

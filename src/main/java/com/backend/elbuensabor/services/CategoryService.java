package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;

public interface CategoryService extends GenericService<Category, CategoryDTO, Long>{
    public Category saveCategory(CategoryDTO dto) throws Exception;
    public Category updateCategory(Long id, CategoryDTO dto) throws Exception;
    public Category blockUnlockCategory(Long id, boolean blocked) throws Exception;

}

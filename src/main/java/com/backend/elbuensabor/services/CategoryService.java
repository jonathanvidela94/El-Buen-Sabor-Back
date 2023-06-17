package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;

import java.util.List;

public interface CategoryService extends GenericService<Category, CategoryDTO, Long>{
    Category saveCategory(CategoryDTO dto) throws Exception;
    Category updateCategory(Long id, CategoryDTO dto) throws Exception;
    Category blockUnlockCategory(Long id, boolean blocked) throws Exception;
    List<CategoryDTO> findUnlockedCategories() throws Exception;
    List<CategoryDTO> findUnlockedCategoriesExceptId(Long id) throws Exception;
}

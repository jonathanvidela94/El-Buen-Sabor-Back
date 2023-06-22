package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;

import java.util.List;

public interface CategoryService extends GenericService<Category, CategoryDTO, Long>{
    Category saveCategory(CategoryDTO dto) throws Exception;
    Category updateCategory(Long id, CategoryDTO dto) throws Exception;
    Category blockUnlockCategory(Long id, boolean blocked) throws Exception;
    List<CategoryDTO> findUnlockedCategoriesByItemType(Long itemTypeId) throws Exception;
    List<CategoryDTO> findUnlockedCategoriesByItemTypeExceptId(Long itemTypeId, Long id) throws Exception;
}

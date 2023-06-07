package com.backend.elbuensabor.services;

import com.backend.elbuensabor.entities.Category;

public interface CategoryService extends GenericService<Category,Long>{

    public void saveCategoryWithParent(Category newCategory, Long parentId) throws Exception;
    public Category lockUnlockCategory(Long id, boolean blocked) throws Exception;
}

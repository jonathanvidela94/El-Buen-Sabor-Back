package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.repositories.CategoryRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category,Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(GenericRepository<Category, Long> genericRepository) {
        super(genericRepository);
    }

//    @Override
//    @Transactional
//    public List<Category> findAll() throws Exception {
//        try {
//            List<Category> categories = categoryRepository.findAllByIsBanned();
//            return categories;
//        }
//        catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

    @Override
    @Transactional
    public Category findById(Long id) throws Exception {
        try {
            Optional<Category> opt = this.categoryRepository.findByIdAndIsBanned(id);
            return opt.get();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void saveCategoryWithParent(Category newCategory, Long parentId) {
        Category parentCategory = categoryRepository.findById(parentId).orElse(null);
        newCategory.setFatherCategory(parentCategory);
        categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            Optional<Category> opt = this.categoryRepository.findById(id);
            if (!opt.isEmpty()) {
                Category category = opt.get();
                category.setIsBanned(!category.getIsBanned());
                this.categoryRepository.save(category);
            }
            else {
                throw  new Exception();
            }
            return true;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

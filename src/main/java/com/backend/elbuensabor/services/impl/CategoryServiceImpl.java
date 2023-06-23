package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.entities.ItemType;
import com.backend.elbuensabor.mappers.CategoryMapper;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.repositories.CategoryRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.ItemTypeRepository;
import com.backend.elbuensabor.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryDTO, Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    private final CategoryMapper categoryMapper = CategoryMapper.getInstance();
    public CategoryServiceImpl(GenericRepository<Category, Long> genericRepository, GenericMapper<Category, CategoryDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    @Transactional
    public Category saveCategory(CategoryDTO dto) throws Exception {
        try {
            Category category = categoryMapper.toEntity(dto);

            if(dto.getCategoryFatherId() != null) {
               if(categoryRepository.existsById(dto.getCategoryFatherId())) {
                   Category fatherCategory = categoryRepository.findById(dto.getCategoryFatherId()).get();
                   category.setFatherCategory(fatherCategory);
               } else {
                   throw new Exception("La categoría padre no existe");
               }
            }

            if(dto.getItemTypeId() != null) {
                if(itemTypeRepository.existsById(dto.getItemTypeId())) {
                    ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId()).get();
                    category.setItemType(itemType);
                } else {
                    throw new Exception("El tipoi de item no existe");
                }
            }

            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    @Override
    @Transactional
    public Category updateCategory(Long id, CategoryDTO dto) throws Exception {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(id);

            if (optionalCategory.isEmpty()) {
                throw new Exception("La categoria a actualizar no existe.");
            }

            Category category = optionalCategory.get();

            if (dto.getCategoryFatherId() != null) {
                if(categoryRepository.existsById(dto.getCategoryFatherId())){
                    Category categoryFather = categoryRepository.findById(dto.getCategoryFatherId()).get();
                    category.setFatherCategory(categoryFather);
                }
                else {
                    throw new Exception("La categoria padre no existe");
                }
            } else {
                category.setFatherCategory(null);
            }

            if(dto.getItemTypeId() != null) {
                if(itemTypeRepository.existsById(dto.getItemTypeId())) {
                    ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId()).get();
                    category.setItemType(itemType);
                } else {
                    throw new Exception("El tipoi de item no existe");
                }
            }

            category.setDenomination(dto.getDenomination());
            category.setBlocked(dto.getBlocked());
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Category blockUnlockCategory(Long id, boolean blocked) throws Exception{
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
            category.setBlocked(blocked);
            return categoryRepository.save(category);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<CategoryDTO>findUnlockedCategoriesByItemType(Long itemTypeId) throws Exception{
        try {
            List<Category> categories = categoryRepository.findUnlockedCategoriesByItemType(itemTypeId);
            return genericMapper.toDTOsList(categories);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<CategoryDTO>findUnlockedCategoriesByItemTypeExceptId(Long itemTypeId, Long id) throws Exception{
        try {
            List<Category> categories = categoryRepository.findUnlockedCategoriesByItemTypeExceptId(itemTypeId, id);
            return genericMapper.toDTOsList(categories);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Fixing Categories front-end :D
    public List<CategoryDTO>findUnlockedCategories() throws Exception{
        try {
            List<Category> categories = categoryRepository.findUnlockedCategories();
            return genericMapper.toDTOsList(categories);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

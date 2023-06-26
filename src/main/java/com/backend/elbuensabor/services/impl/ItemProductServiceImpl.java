package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.IngredientDTO;
import com.backend.elbuensabor.DTO.ItemProductDTO;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemProductMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.ItemProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemProductServiceImpl extends GenericServiceImpl<Item, ItemProductDTO, Long> implements ItemProductService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeDetailRepository recipeDetailRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    private final ItemProductMapper itemProductMapper = ItemProductMapper.getInstance();

    public ItemProductServiceImpl(GenericRepository<Item, Long> genericRepository, GenericMapper<Item, ItemProductDTO> genericMapper){
        super(genericRepository, genericMapper);
    }

    @Override
    @Transactional
    public Item saveProduct(ItemProductDTO dto) throws Exception {
        try {
            Item item = itemProductMapper.toEntity(dto);

            if(dto.getCategoryId() != null) {
                if(categoryRepository.existsById(dto.getCategoryId())) {
                    Category category = categoryRepository.findById(dto.getCategoryId()).get();
                    item.setCategory(category);
                } else {
                    throw new Exception("La categoría no existe");
                }
            }

            if(dto.getItemTypeId() != null) {
                if(dto.getItemTypeId() != null) {
                    if(itemTypeRepository.existsById(dto.getItemTypeId())) {
                        ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId()).get();
                        item.setItemType(itemType);
                    } else {
                        throw new Exception("El tipo de item no existe");
                    }
                }
            }

            Item savedItem = itemRepository.save(item);

            Recipe recipe = new Recipe();
            recipe.setItem(item);
            recipe.setDescription(dto.getRecipeDescription());

            for (IngredientDTO ingredientDTO : dto.getIngredients()) {
                RecipeDetail recipeDetail = new RecipeDetail();

                Optional<Item> itemIngredientOptional = itemRepository.findById(ingredientDTO.getIdIngredient());
                if (itemIngredientOptional.isPresent()) {
                    Item itemIngredient = itemIngredientOptional.get();
                    recipeDetail.setItem(itemIngredient);
                } else {
                    throw new Exception("El ingrediente con ID " + ingredientDTO.getIdIngredient() + " no existe");
                }

                recipeDetail.setRecipe(recipe);
                recipeDetail.setQuantity(ingredientDTO.getQuantity());

                recipe.getRecipeDetails().add(recipeDetail);
            }

            return savedItem;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

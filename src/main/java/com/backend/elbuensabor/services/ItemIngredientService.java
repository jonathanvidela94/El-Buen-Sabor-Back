package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.ItemIngredientDTO;
import com.backend.elbuensabor.entities.Item;

import java.util.List;

public interface ItemIngredientService extends GenericService<Item, ItemIngredientDTO, Long>{
    List<ItemIngredientDTO> getAllItemsIngredients() throws Exception;
    ItemIngredientDTO getItemIngredient(Long id) throws Exception;
    Item saveItemIngredient(ItemIngredientDTO dto) throws Exception;
    ItemIngredientDTO updateItemIngredient(Long ID, ItemIngredientDTO dto) throws Exception;
    Item blockUnlockItem(Long id, boolean blocked) throws Exception;
}

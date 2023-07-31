package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.ItemDrinkDTO;
import com.backend.elbuensabor.entities.Item;

import java.util.List;

public interface ItemDrinkService extends GenericService <Item, ItemDrinkDTO, Long>{
    List<ItemDrinkDTO> getAllDrinks() throws Exception;
    ItemDrinkDTO getItemDrink(Long id) throws Exception;
    Item saveDrink(ItemDrinkDTO dto) throws Exception;
    ItemDrinkDTO updateItemDrink(Long id, ItemDrinkDTO itemDrinkDTO) throws Exception;
}

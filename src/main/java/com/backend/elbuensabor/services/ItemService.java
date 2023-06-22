package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.ItemDTO;
import com.backend.elbuensabor.entities.Item;

import java.util.List;

public interface ItemService extends GenericService<Item, ItemDTO, Long>{

    List<ItemDTO> getAllItems() throws Exception;
    Item saveItem(ItemDTO dto) throws Exception;
    ItemDTO getItem(Long id) throws Exception;
    ItemDTO updateItem(Long ID, ItemDTO dto) throws Exception;

}

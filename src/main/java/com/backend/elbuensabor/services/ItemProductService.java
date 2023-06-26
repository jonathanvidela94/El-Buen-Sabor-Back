package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.ItemProductDTO;
import com.backend.elbuensabor.entities.Item;

import java.util.List;

public interface ItemProductService extends GenericService <Item, ItemProductDTO, Long> {
    List<ItemProductDTO> getAllProducts() throws Exception;
    ItemProductDTO getItemProduct(Long id) throws Exception;
    Item saveProduct(ItemProductDTO dto) throws Exception;
    ItemProductDTO updateItemProduct(Long ID, ItemProductDTO dto) throws Exception;
}

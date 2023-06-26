package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.ItemProductDTO;
import com.backend.elbuensabor.entities.Item;

public interface ItemProductService extends GenericService <Item, ItemProductDTO, Long> {
    Item saveProduct(ItemProductDTO dto) throws Exception;
}

package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemCurrentStockDTO;
import com.backend.elbuensabor.entities.ItemCurrentStock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemCurrentStockMapper extends GenericMapper<ItemCurrentStock, ItemCurrentStockDTO> {
    static ItemCurrentStockMapper getInstance() { return Mappers.getMapper(ItemCurrentStockMapper.class); }
}

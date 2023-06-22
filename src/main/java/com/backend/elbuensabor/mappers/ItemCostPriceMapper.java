package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemCostPriceDTO;
import com.backend.elbuensabor.entities.ItemCostPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemCostPriceMapper extends GenericMapper<ItemCostPrice, ItemCostPriceDTO> {
    static ItemCostPriceMapper getInstance() { return Mappers.getMapper(ItemCostPriceMapper.class); }
}

package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemStockConfigDTO;
import com.backend.elbuensabor.entities.ItemStockConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemStockConfigMapper extends GenericMapper<ItemStockConfig, ItemStockConfigDTO> {
    static ItemStockConfigMapper getInstance() { return Mappers.getMapper(ItemStockConfigMapper.class); }
}

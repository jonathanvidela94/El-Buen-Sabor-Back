package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemSellPriceDTO;
import com.backend.elbuensabor.entities.ItemSellPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemSellPriceMapper extends GenericMapper<ItemSellPrice, ItemSellPriceDTO> {
    static ItemSellPriceMapper getInstance() {
        return Mappers.getMapper(ItemSellPriceMapper.class);
    }
}

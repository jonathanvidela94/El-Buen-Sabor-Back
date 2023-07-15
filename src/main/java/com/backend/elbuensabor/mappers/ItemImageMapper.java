package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemImageDTO;
import com.backend.elbuensabor.entities.ItemImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemImageMapper extends GenericMapper<ItemImage, ItemImageDTO> {
    static ItemImageMapper getInstance() {
        return Mappers.getMapper(ItemImageMapper.class);
    }
}

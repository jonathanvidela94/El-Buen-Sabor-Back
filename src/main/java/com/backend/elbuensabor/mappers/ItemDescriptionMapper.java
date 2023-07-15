package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemDescriptionDTO;
import com.backend.elbuensabor.entities.ItemDescription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemDescriptionMapper extends GenericMapper<ItemDescription, ItemDescriptionDTO> {
    static ItemDescriptionMapper getInstance() { return Mappers.getMapper(ItemDescriptionMapper.class); }
}

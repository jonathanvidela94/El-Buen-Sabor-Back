package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemTypeDTO;
import com.backend.elbuensabor.entities.ItemType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemTypeMapper extends GenericMapper<ItemType, ItemTypeDTO> {
    static ItemTypeMapper getInstance() { return Mappers.getMapper(ItemTypeMapper.class); }
}

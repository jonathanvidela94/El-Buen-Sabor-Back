package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemProductDTO;
import com.backend.elbuensabor.entities.Item;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemProductMapper extends GenericMapper<Item, ItemProductDTO> {

    static ItemProductMapper getInstance() {
        return Mappers.getMapper(ItemProductMapper.class);
    }

    @Mapping(source = "source.category.id", target = "categoryId")
    @Mapping(source = "source.category.denomination", target = "categoryDenomination")
    @Mapping(source = "source.itemType.id", target = "itemTypeId")
    ItemProductDTO toDTO(Item source);

    @InheritInverseConfiguration
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "itemType", ignore = true)
    Item toEntity(ItemProductDTO source);

}

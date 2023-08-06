package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemDrinkDTO;
import com.backend.elbuensabor.entities.Item;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemDrinkMapper extends GenericMapper<Item, ItemDrinkDTO>{

    static ItemDrinkMapper getInstance() {
        return Mappers.getMapper(ItemDrinkMapper.class);
    }

    @Mapping(source = "source.category.id", target = "categoryId")
    @Mapping(source = "source.category.denomination", target = "categoryDenomination")
    @Mapping(source = "source.itemType.id", target = "itemTypeId")
    ItemDrinkDTO toDTO(Item source);

    @InheritInverseConfiguration
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "itemType", ignore = true)
    Item toEntity(ItemDrinkDTO source);
}

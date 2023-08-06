package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemIngredientDTO;
import com.backend.elbuensabor.entities.Item;
import com.backend.elbuensabor.entities.ItemMeasurementUnit;
import com.backend.elbuensabor.entities.MeasurementUnit;
import com.backend.elbuensabor.repositories.MeasurementUnitRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemIngredientMapper extends GenericMapper<Item, ItemIngredientDTO> {

    static ItemIngredientMapper getInstance() { return Mappers.getMapper(ItemIngredientMapper.class); }

    @Mapping(source = "source.category.id", target = "categoryId")
    @Mapping(source = "source.category.denomination", target = "categoryDenomination")
    @Mapping(source = "source.itemType.id", target = "itemTypeId")
    ItemIngredientDTO toDTO(Item source);

    @InheritInverseConfiguration
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "itemType", ignore = true)
    Item toEntity(ItemIngredientDTO source);

    @AfterMapping
    default ItemMeasurementUnit mapMeasurementUnit(ItemIngredientDTO source, @MappingTarget Item target, MeasurementUnitRepository measurementUnitRepository) {
        MeasurementUnit measurementUnit = measurementUnitRepository.findById(source.getMeasurementUnitId())
                .orElseThrow(() -> new RuntimeException("MeasurementUnit not found"));
        ItemMeasurementUnit itemMeasurementUnit = new ItemMeasurementUnit();
        itemMeasurementUnit.setItem(target);
        itemMeasurementUnit.setMeasurementUnit(measurementUnit);
        return itemMeasurementUnit;
    }

    @AfterMapping
    default void mapMeasurementUnitToDTO(@MappingTarget ItemIngredientDTO target, Item source, ItemMeasurementUnit itemMeasurementUnit) {
        target.setMeasurementUnitId(itemMeasurementUnit.getMeasurementUnit().getId());
        target.setMeasurementDenomination(itemMeasurementUnit.getMeasurementUnit().getDenomination());
    }

}
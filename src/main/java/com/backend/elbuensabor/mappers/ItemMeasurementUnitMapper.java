package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.ItemMeasurementUnitDTO;
import com.backend.elbuensabor.entities.ItemMeasurementUnit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMeasurementUnitMapper extends GenericMapper<ItemMeasurementUnit, ItemMeasurementUnitDTO> {
    static ItemMeasurementUnitMapper getInstance() {return Mappers.getMapper(ItemMeasurementUnitMapper.class);}
}

package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.MeasurementUnitDTO;
import com.backend.elbuensabor.entities.MeasurementUnit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MeasurementUnitMapper extends GenericMapper <MeasurementUnit, MeasurementUnitDTO> {
    static MeasurementUnitMapper getInstance() { return Mappers.getMapper(MeasurementUnitMapper.class); }
}

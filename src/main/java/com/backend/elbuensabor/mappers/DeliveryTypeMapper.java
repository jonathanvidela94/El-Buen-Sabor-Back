package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.DeliveryTypeDTO;
import com.backend.elbuensabor.entities.DeliveryType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryTypeMapper extends GenericMapper<DeliveryType, DeliveryTypeDTO>{
    static DeliveryTypeMapper getInstance() {return Mappers.getMapper(DeliveryTypeMapper.class);}
}

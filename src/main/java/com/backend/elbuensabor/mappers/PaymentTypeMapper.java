package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.PaymentTypeDTO;
import com.backend.elbuensabor.entities.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper extends GenericMapper<PaymentType, PaymentTypeDTO>{
    static PaymentTypeMapper getInstance() {return Mappers.getMapper(PaymentTypeMapper.class);}
}

package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.Orders;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapper extends GenericMapper<Orders, OrdersDTO>{
}

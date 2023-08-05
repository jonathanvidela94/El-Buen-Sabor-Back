package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.OrderStatusDTO;
import com.backend.elbuensabor.entities.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper extends GenericMapper<OrderStatus, OrderStatusDTO>{
    static OrderStatusMapper getInstance() {return Mappers.getMapper(OrderStatusMapper.class);}
}

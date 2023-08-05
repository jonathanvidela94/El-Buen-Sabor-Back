package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.OrderDetailDTO;
import com.backend.elbuensabor.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper extends GenericMapper<OrderDetail, OrderDetailDTO>{
    static OrderDetailMapper getInstance() {return Mappers.getMapper(OrderDetailMapper.class);}

    @Override
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "orderId", source = "order.id")
    OrderDetailDTO toDTO(OrderDetail source);

}

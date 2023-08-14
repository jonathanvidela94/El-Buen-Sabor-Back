package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface OrdersMapper extends GenericMapper<Orders, OrdersDTO>{
    static OrdersMapper getInstance() {
        return Mappers.getMapper(OrdersMapper.class);
    }

    @Mapping(source = "source.deliveryType.id", target = "deliveryTypeId")
    @Mapping(source = "source.orderStatus.id", target = "orderStatusId")
    @Mapping(source = "source.paymentType.id", target = "paymentTypeId")
    @Mapping(source = "source.customer.id", target = "customerId")
    OrdersDTO toDTO(Orders source);

    @Mapping(target = "deliveryType", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "paymentType", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    Orders toEntity(OrdersDTO source);

}

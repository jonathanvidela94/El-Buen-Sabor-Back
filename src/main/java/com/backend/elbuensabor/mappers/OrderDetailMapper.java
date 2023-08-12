package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.OrderDetailDTO;
import com.backend.elbuensabor.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper extends GenericMapper<OrderDetail, OrderDetailDTO>{

    static OrderDetailMapper getInstance() {
        return Mappers.getMapper(OrderDetailMapper.class);
    }

    @Mapping(source = "source.item.id", target = "itemId")
    OrderDetailDTO toDTO(OrderDetail source);

    @Mapping(target = "item", ignore = true)
    OrderDetail toEntity(OrderDetailDTO source);
}

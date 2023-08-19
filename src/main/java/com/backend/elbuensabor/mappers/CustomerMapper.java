package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.CustomerDTO;
import com.backend.elbuensabor.entities.Customer;
import com.backend.elbuensabor.entities.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO>{

    static CustomerMapper getInstance() { return Mappers.getMapper(CustomerMapper.class); }

    @Mapping(source = "source.orders", target = "orderIds")
    CustomerDTO toDTO(Customer source);

    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDTO source);

    default List<Long> map(List<Orders> orders) {
        if (orders == null) {
            return null;
        }

        List<Long> list = new ArrayList<>(orders.size());
        for (Orders order : orders) {
            list.add(order.getId());
        }

        return list;
    }

}

package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.CustomerDTO;
import com.backend.elbuensabor.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO>{

    static CustomerMapper getInstance() { return Mappers.getMapper(CustomerMapper.class); }

}

package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CustomerDTO;
import com.backend.elbuensabor.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends GenericService<Customer, CustomerDTO, Long>{
    List<CustomerDTO> findAllCustomersWithDifferentRoleId(Long roleId) throws Exception;
    CustomerDTO findCustomerByUserAuth0Id(String auth0Id) throws Exception;
}

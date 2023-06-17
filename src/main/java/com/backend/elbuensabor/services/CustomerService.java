package com.backend.elbuensabor.services;

import com.backend.elbuensabor.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends GenericService<Customer,Long>{
    List<Customer> findAllCustomersWithDifferentRoleId(Long roleId);

    Optional<Customer> findCustomerByUserAuth0Id(String auth0Id);
}

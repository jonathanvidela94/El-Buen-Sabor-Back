package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.Customer;
import com.backend.elbuensabor.repositories.CustomerRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer,Long> implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(GenericRepository<Customer, Long> genericRepository) {
        super(genericRepository);
    }

    @Override
    public List<Customer> findAllCustomersWithDifferentRoleId(Long roleId) {
        return customerRepository.findAllCustomersWithDifferentRoleId(roleId);
    }
}

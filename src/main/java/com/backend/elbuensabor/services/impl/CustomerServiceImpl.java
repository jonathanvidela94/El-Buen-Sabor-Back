package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.CustomerDTO;
import com.backend.elbuensabor.entities.Customer;
import com.backend.elbuensabor.mappers.CustomerMapper;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.repositories.CustomerRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, CustomerDTO, Long> implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = CustomerMapper.getInstance();
    public CustomerServiceImpl(GenericRepository<Customer, Long> genericRepository, GenericMapper<Customer, CustomerDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    public List<CustomerDTO> findAllCustomersWithDifferentRoleId(Long roleId) throws Exception{
        try {
            List<Customer> customers = customerRepository.findAllCustomersWithDifferentRoleId(roleId);
            return genericMapper.toDTOsList(customers);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CustomerDTO findCustomerByUserAuth0Id(String auth0Id) throws Exception{
        try {
            Customer customer = customerRepository.findCustomerByUserAuth0Id(auth0Id);
            return customerMapper.toDTO(customer);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

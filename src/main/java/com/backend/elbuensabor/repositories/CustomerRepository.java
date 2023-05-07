package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer,Long>{
}

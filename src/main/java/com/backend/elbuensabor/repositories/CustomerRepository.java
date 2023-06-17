package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends GenericRepository<Customer,Long>{
    @Query("SELECT c FROM Customer c JOIN FETCH c.user u JOIN FETCH u.role r WHERE r.id <> :roleId")
    List<Customer> findAllCustomersWithDifferentRoleId(@Param("roleId") Long roleId);

}

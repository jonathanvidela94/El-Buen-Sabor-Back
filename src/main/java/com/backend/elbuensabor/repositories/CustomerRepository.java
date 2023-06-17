package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends GenericRepository<Customer,Long>{
    @Query("SELECT c FROM Customer c JOIN FETCH c.user u JOIN FETCH u.role r WHERE r.id <> :roleId")
    List<Customer> findAllCustomersWithDifferentRoleId(@Param("roleId") Long roleId);

    @Query("SELECT c FROM Customer c JOIN FETCH c.user u WHERE u.auth0Id = :auth0Id")
    Customer findCustomerByUserAuth0Id(@Param("auth0Id") String auth0Id);
}

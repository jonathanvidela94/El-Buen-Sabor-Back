package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends GenericRepository<Customer,Long>{

    //Se puede usar para obtener a los empleados o a los clientes.
    //Nativo
    //@Query(value = "SELECT c.* FROM customer c INNER JOIN user u ON c.fk_user = u.id INNER JOIN role r ON u.fk_role = r.id WHERE r.id <> :roleId", nativeQuery = true)
    //JPQL
    @Query("SELECT c FROM Customer c JOIN FETCH c.user u JOIN FETCH u.role r WHERE r.id <> :roleId")
    List<Customer> findAllCustomersWithDifferentRoleId(@Param("roleId") Long roleId);
}

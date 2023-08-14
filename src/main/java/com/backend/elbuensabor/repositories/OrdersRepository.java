package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends GenericRepository<Orders, Long>{

    @EntityGraph(attributePaths = {"orderDetails", "orderDetails.item"})
    List<Orders> findAll();

}

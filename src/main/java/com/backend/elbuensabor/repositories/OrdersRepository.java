package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface OrdersRepository extends GenericRepository<Orders, Long>{

    @EntityGraph(attributePaths = {"orderDetails", "orderDetails.item"})
    List<Orders> findAll();

    @EntityGraph(attributePaths = {"orderDetails", "orderDetails.item"})
    List<Orders> findAllByCustomerId(Long id);

    @Query("SELECT o FROM Orders o WHERE o.orderStatus.id = :statusId")
    List<Orders> findAllByOrderStatusId(@Param("statusId") Long statusId);
}

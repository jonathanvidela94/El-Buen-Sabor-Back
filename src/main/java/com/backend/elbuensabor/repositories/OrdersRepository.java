package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.DTO.CustomerSummaryDTO;
import com.backend.elbuensabor.entities.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends GenericRepository<Orders, Long>{
    @Query("SELECT NEW com.backend.elbuensabor.DTO.CustomerSummaryDTO(c.id, c.name, c.lastname, COUNT(o), SUM(o.total)) " +
            "FROM Orders o " +
            "JOIN o.customer c " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "AND o.orderStatus.id = 5 " +
            "GROUP BY c.id, c.name, c.lastname")
    List<CustomerSummaryDTO> getCustomerSummaryBetweenDates(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

}

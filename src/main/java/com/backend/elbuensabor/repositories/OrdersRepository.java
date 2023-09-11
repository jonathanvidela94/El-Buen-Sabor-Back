package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.DTO.CustomerSummaryDTO;
import com.backend.elbuensabor.DTO.ItemSalesDTO;
import com.backend.elbuensabor.DTO.MonetaryMovementsDTO;
import com.backend.elbuensabor.entities.Orders;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends GenericRepository<Orders, Long> {

    @EntityGraph(attributePaths = {"orderDetails", "orderDetails.item"})
    List<Orders> findAll();

    @EntityGraph(attributePaths = {"orderDetails", "orderDetails.item"})
    List<Orders> findAllByCustomerId(Long id);

    @Query("SELECT o FROM Orders o WHERE o.orderStatus.id = :statusId")
    List<Orders> findAllByOrderStatusId(@Param("statusId") Long statusId);

    @Query("SELECT NEW com.backend.elbuensabor.DTO.CustomerSummaryDTO(c.id, c.name, c.lastname, COUNT(o), SUM(o.total)) " +
            "FROM Orders o " +
            "JOIN o.customer c " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "AND o.orderStatus.id = 5 " +
            "GROUP BY c.id, c.name, c.lastname")
    List<CustomerSummaryDTO> getCustomerSummaryBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT NEW com.backend.elbuensabor.DTO.ItemSalesDTO(od.item.id, od.item.name, od.item.itemType.id, SUM(od.quantity)) " +
            "FROM OrderDetail od " +
            "WHERE od.order.orderStatus.id = 5 " +
            "AND od.order.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY od.item.id, od.item.name, od.item.itemType.id " +
            "ORDER BY SUM(od.quantity) DESC")
    List<ItemSalesDTO> getItemsWithSoldQuantitiesBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(nativeQuery = true, value = "SELECT SUM(o.total - o.discount) AS Ingresos, " +
            "SUM((SELECT SUM(od.quantity * (SELECT SUM(rd.quantity * icp.cost_price) " +
            "FROM recipe_detail rd " +
            "INNER JOIN item_cost_price icp ON rd.fk_item = icp.fk_item " +
            "WHERE rd.fk_recipe = r.id)) " +
            "FROM order_detail od " +
            "INNER JOIN recipe r ON od.fk_item = r.fk_item " +
            "WHERE od.fk_order = o.id)) AS Costos, " +
            "SUM(o.total - o.discount) - SUM((SELECT SUM(od.quantity * (SELECT SUM(rd.quantity * icp.cost_price) " +
            "FROM recipe_detail rd " +
            "INNER JOIN item_cost_price icp ON rd.fk_item = icp.fk_item " +
            "WHERE rd.fk_recipe = r.id)) " +
            "FROM order_detail od " +
            "INNER JOIN recipe r ON od.fk_item = r.fk_item " +
            "WHERE od.fk_order = o.id)) AS Ganancias " +
            "FROM orders o " +
            "WHERE o.fk_order_status = 5 " +
            "AND o.order_date BETWEEN :startDate AND :endDate")
    List<Tuple> getMonetaryMovementsBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}

package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CustomerSummaryDTO;
import com.backend.elbuensabor.DTO.ItemSalesDTO;
import com.backend.elbuensabor.DTO.MonetaryMovementsDTO;
import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.Orders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrdersService extends GenericService<Orders, OrdersDTO,Long>{

    List<OrdersDTO> getAllOrders() throws Exception;
    List<OrdersDTO> getAllOrdersByCustomerId(Long id) throws Exception;
    OrdersDTO getOrder(Long id) throws Exception;
    Orders saveOrder(OrdersDTO dto) throws Exception;
    OrdersDTO updateOrder(Long id, OrdersDTO dto) throws Exception;
    OrdersDTO cancelOrder(Long id, OrdersDTO dto) throws Exception;
    List<CustomerSummaryDTO>getCustomerSummaryBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    List<ItemSalesDTO>getItemsWithSoldQuantitiesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    MonetaryMovementsDTO getMonetaryMovementsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws Exception;

}

package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.CustomerSummaryDTO;
import com.backend.elbuensabor.DTO.ItemSalesDTO;
import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.Orders;

import java.util.Date;
import java.util.List;

public interface OrdersService extends GenericService<Orders, OrdersDTO,Long>{

    List<OrdersDTO> getAllOrders() throws Exception;
    List<OrdersDTO> getAllOrdersByCustomerId(Long id) throws Exception;
    OrdersDTO getOrder(Long id) throws Exception;
    Orders saveOrder(OrdersDTO dto) throws Exception;
    OrdersDTO updateOrder(Long id, OrdersDTO dto) throws Exception;
    OrdersDTO cancelOrder(Long id, OrdersDTO dto) throws Exception;
    List<CustomerSummaryDTO>getCustomerSummaryBetweenDates(Date startDate, Date endDate) throws Exception;
    List<ItemSalesDTO>getItemsWithSoldQuantitiesBetweenDates(Date startDate, Date endDate) throws Exception;

}

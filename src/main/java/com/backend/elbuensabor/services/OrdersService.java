package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.Orders;

import java.util.List;

public interface OrdersService extends GenericService<Orders, OrdersDTO,Long>{

    List<OrdersDTO> getAllOrders() throws Exception;
    OrdersDTO getOrder(Long id) throws Exception;
    Orders saveOrder(OrdersDTO dto) throws Exception;
    OrdersDTO updateOrder(Long id, OrdersDTO dto) throws Exception;

}

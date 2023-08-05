package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.Orders;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.OrdersMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.OrdersRepository;
import com.backend.elbuensabor.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl extends GenericServiceImpl<Orders, OrdersDTO,Long> implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;


    private final OrdersMapper ordersMapper = OrdersMapper.getInstance();

    public OrdersServiceImpl(GenericRepository<Orders, Long> genericRepository, GenericMapper<Orders, OrdersDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    public List<OrdersDTO> getAllOrders() throws Exception {
        return null;
    }

    @Override
    public OrdersDTO getOrder(Long id) throws Exception {
        return null;
    }

    @Override
    public Orders saveOrder(OrdersDTO dto) throws Exception {
        return null;
    }

    @Override
    public OrdersDTO updateOrder(Long id, OrdersDTO dto) throws Exception {
        return null;
    }
}

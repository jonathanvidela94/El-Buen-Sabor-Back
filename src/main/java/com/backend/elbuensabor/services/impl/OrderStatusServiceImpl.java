package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.OrderStatusDTO;
import com.backend.elbuensabor.entities.OrderStatus;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.OrderStatusMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.OrderStatusRepository;
import com.backend.elbuensabor.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl extends GenericServiceImpl<OrderStatus, OrderStatusDTO,Long> implements OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    private final OrderStatusMapper orderStatusMapper = OrderStatusMapper.getInstance();

    public OrderStatusServiceImpl(GenericRepository<OrderStatus, Long> genericRepository, GenericMapper<OrderStatus, OrderStatusDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

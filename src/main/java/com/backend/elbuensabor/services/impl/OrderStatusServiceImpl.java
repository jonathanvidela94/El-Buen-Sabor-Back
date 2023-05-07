package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.OrderStatus;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.OrderStatusRepository;
import com.backend.elbuensabor.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl extends GenericServiceImpl<OrderStatus,Long> implements OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(GenericRepository<OrderStatus, Long> genericRepository) {
        super(genericRepository);
    }
}

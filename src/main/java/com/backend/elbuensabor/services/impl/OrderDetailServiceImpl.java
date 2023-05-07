package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.OrderDetail;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.OrderDetailRepository;
import com.backend.elbuensabor.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends GenericServiceImpl<OrderDetail,Long> implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(GenericRepository<OrderDetail, Long> genericRepository) {
        super(genericRepository);
    }
}

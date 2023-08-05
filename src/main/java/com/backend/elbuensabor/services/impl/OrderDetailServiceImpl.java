package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.OrderDetailDTO;
import com.backend.elbuensabor.entities.OrderDetail;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.OrderDetailMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.OrderDetailRepository;
import com.backend.elbuensabor.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends GenericServiceImpl<OrderDetail, OrderDetailDTO,Long> implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private final OrderDetailMapper orderDetailMapper = OrderDetailMapper.getInstance();

    public OrderDetailServiceImpl(GenericRepository<OrderDetail, Long> genericRepository, GenericMapper<OrderDetail, OrderDetailDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

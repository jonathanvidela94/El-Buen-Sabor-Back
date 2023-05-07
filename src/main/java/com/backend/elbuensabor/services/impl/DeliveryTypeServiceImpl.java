package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.DeliveryType;
import com.backend.elbuensabor.repositories.DeliveryTypeRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTypeServiceImpl extends GenericServiceImpl<DeliveryType,Long> implements DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    public DeliveryTypeServiceImpl(GenericRepository<DeliveryType, Long> genericRepository) {
        super(genericRepository);
    }
}

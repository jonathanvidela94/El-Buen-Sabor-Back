package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.DeliveryTypeDTO;
import com.backend.elbuensabor.entities.DeliveryType;
import com.backend.elbuensabor.mappers.DeliveryTypeMapper;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.repositories.DeliveryTypeRepository;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTypeServiceImpl extends GenericServiceImpl<DeliveryType, DeliveryTypeDTO,Long> implements DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    private final DeliveryTypeMapper deliveryTypeMapper = DeliveryTypeMapper.getInstance();

    public DeliveryTypeServiceImpl(GenericRepository<DeliveryType, Long> genericRepository, GenericMapper<DeliveryType, DeliveryTypeDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

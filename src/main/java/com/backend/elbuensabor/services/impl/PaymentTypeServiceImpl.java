package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.PaymentTypeDTO;
import com.backend.elbuensabor.entities.PaymentType;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.PaymentTypeMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.PaymentTypeRepository;
import com.backend.elbuensabor.services.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeServiceImpl extends GenericServiceImpl<PaymentType, PaymentTypeDTO,Long> implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    private final PaymentTypeMapper paymentTypeMapper = PaymentTypeMapper.getInstance();

    public PaymentTypeServiceImpl(GenericRepository<PaymentType, Long> genericRepository, GenericMapper<PaymentType, PaymentTypeDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

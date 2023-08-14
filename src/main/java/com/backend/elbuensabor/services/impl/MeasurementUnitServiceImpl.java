package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.MeasurementUnitDTO;
import com.backend.elbuensabor.entities.MeasurementUnit;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.MeasurementUnitMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.MeasurementUnitRepository;
import com.backend.elbuensabor.services.MeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementUnitServiceImpl extends GenericServiceImpl<MeasurementUnit, MeasurementUnitDTO, Long> implements MeasurementUnitService {
    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    private final MeasurementUnitMapper measurementUnitMapper = MeasurementUnitMapper.getInstance();

    public MeasurementUnitServiceImpl(GenericRepository<MeasurementUnit, Long> genericRepository, GenericMapper<MeasurementUnit, MeasurementUnitDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

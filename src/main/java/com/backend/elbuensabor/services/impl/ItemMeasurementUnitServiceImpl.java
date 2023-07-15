package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemMeasurementUnitDTO;
import com.backend.elbuensabor.entities.ItemMeasurementUnit;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemMeasurementUnitMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.ItemMeasurementUnitRepository;
import com.backend.elbuensabor.services.ItemMeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMeasurementUnitServiceImpl extends GenericServiceImpl<ItemMeasurementUnit, ItemMeasurementUnitDTO, Long> implements ItemMeasurementUnitService {

    @Autowired
    private ItemMeasurementUnitRepository itemMeasurementUnitRepository;

    private final ItemMeasurementUnitMapper itemMeasurementUnitMapper = ItemMeasurementUnitMapper.getInstance();

    public ItemMeasurementUnitServiceImpl(GenericRepository<ItemMeasurementUnit, Long> genericRepository, GenericMapper <ItemMeasurementUnit, ItemMeasurementUnitDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

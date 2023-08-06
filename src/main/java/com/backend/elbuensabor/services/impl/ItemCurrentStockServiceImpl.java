package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemCurrentStockDTO;
import com.backend.elbuensabor.entities.ItemCurrentStock;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemCurrentStockMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.ItemCurrentStockRepository;
import com.backend.elbuensabor.services.ItemCurrentStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCurrentStockServiceImpl extends GenericServiceImpl<ItemCurrentStock, ItemCurrentStockDTO, Long> implements ItemCurrentStockService {
    @Autowired
    private ItemCurrentStockRepository itemCurrentStockRepository;

    private final ItemCurrentStockMapper itemCurrentStockMapper = ItemCurrentStockMapper.getInstance();

    public ItemCurrentStockServiceImpl(GenericRepository<ItemCurrentStock, Long> genericRepository, GenericMapper <ItemCurrentStock, ItemCurrentStockDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

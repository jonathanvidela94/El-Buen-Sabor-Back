package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemCostPriceDTO;
import com.backend.elbuensabor.entities.ItemCostPrice;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemCostPriceMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.ItemCostPriceRepository;
import com.backend.elbuensabor.services.ItemCostPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCostPriceServiceImpl extends GenericServiceImpl<ItemCostPrice, ItemCostPriceDTO, Long> implements ItemCostPriceService {

    @Autowired
    private ItemCostPriceRepository itemCostPriceRepository;

    private final ItemCostPriceMapper itemCostPriceMapper = ItemCostPriceMapper.getInstance();

    public ItemCostPriceServiceImpl(GenericRepository<ItemCostPrice, Long> genericRepository, GenericMapper <ItemCostPrice, ItemCostPriceDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

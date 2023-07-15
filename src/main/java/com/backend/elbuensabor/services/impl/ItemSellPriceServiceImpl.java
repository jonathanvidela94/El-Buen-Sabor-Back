package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemSellPriceDTO;
import com.backend.elbuensabor.entities.ItemSellPrice;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.ItemSellPriceRepository;
import com.backend.elbuensabor.services.ItemSellPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSellPriceServiceImpl extends GenericServiceImpl<ItemSellPrice, ItemSellPriceDTO, Long> implements ItemSellPriceService {
    @Autowired
    private ItemSellPriceRepository itemSellPriceRepository;

    public ItemSellPriceServiceImpl(GenericRepository<ItemSellPrice, Long> genericRepository, GenericMapper <ItemSellPrice, ItemSellPriceDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

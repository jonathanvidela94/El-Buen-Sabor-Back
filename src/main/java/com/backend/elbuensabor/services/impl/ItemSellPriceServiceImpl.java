package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.ItemSellPrice;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.ItemSellPriceRepository;
import com.backend.elbuensabor.services.ItemSellPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSellPriceServiceImpl extends GenericServiceImpl<ItemSellPrice,Long> implements ItemSellPriceService {
    @Autowired
    private ItemSellPriceRepository itemSellPriceRepository;

    public ItemSellPriceServiceImpl(GenericRepository<ItemSellPrice, Long> genericRepository) {
        super(genericRepository);
    }
}

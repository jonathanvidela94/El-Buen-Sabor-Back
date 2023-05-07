package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.ItemCostPrice;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.ItemCostPriceRepository;
import com.backend.elbuensabor.services.ItemCostPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCostPriceServiceImpl extends GenericServiceImpl<ItemCostPrice,Long> implements ItemCostPriceService {

    @Autowired
    private ItemCostPriceRepository itemCostPriceRepository;

    public ItemCostPriceServiceImpl(GenericRepository<ItemCostPrice, Long> genericRepository) {
        super(genericRepository);
    }
}

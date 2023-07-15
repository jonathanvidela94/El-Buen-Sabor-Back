package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemStockConfigDTO;
import com.backend.elbuensabor.entities.ItemStockConfig;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemStockConfigMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.ItemStockConfigRepository;
import com.backend.elbuensabor.services.ItemStockConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemStockConfigServiceImpl extends GenericServiceImpl<ItemStockConfig, ItemStockConfigDTO, Long> implements ItemStockConfigService {
    @Autowired
    private ItemStockConfigRepository itemStockConfigRepository;

    private final ItemStockConfigMapper itemCurrentStockMapper = ItemStockConfigMapper.getInstance();

    public ItemStockConfigServiceImpl(GenericRepository<ItemStockConfig, Long> genericRepository, GenericMapper <ItemStockConfig, ItemStockConfigDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

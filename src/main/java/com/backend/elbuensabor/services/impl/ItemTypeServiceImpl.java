package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemTypeDTO;
import com.backend.elbuensabor.entities.ItemType;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemTypeMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.ItemTypeRepository;
import com.backend.elbuensabor.services.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeServiceImpl extends GenericServiceImpl<ItemType, ItemTypeDTO, Long> implements ItemTypeService {
    @Autowired
    private ItemTypeRepository itemTypeRepository;

    private final ItemTypeMapper itemTypeMapper = ItemTypeMapper.getInstance();

    public ItemTypeServiceImpl(GenericRepository<ItemType, Long> genericRepository, GenericMapper<ItemType, ItemTypeDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

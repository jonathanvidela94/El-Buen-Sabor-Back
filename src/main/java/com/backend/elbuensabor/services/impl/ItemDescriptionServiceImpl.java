package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemDescriptionDTO;
import com.backend.elbuensabor.entities.ItemDescription;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.impl.repositories.ItemDescriptionRepository;
import com.backend.elbuensabor.services.ItemDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescriptionServiceImpl extends GenericServiceImpl<ItemDescription, ItemDescriptionDTO, Long> implements ItemDescriptionService {

    @Autowired
    private ItemDescriptionRepository itemDescriptionRepository;

    public ItemDescriptionServiceImpl(GenericRepository<ItemDescription, Long> genericRepository, GenericMapper<ItemDescription, ItemDescriptionDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

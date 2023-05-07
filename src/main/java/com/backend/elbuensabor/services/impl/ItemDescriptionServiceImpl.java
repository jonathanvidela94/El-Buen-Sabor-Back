package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.ItemDescription;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.ItemDescriptionRepository;
import com.backend.elbuensabor.services.ItemDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescriptionServiceImpl extends GenericServiceImpl<ItemDescription,Long> implements ItemDescriptionService {

    @Autowired
    private ItemDescriptionRepository itemDescriptionRepository;

    public ItemDescriptionServiceImpl(GenericRepository<ItemDescription, Long> genericRepository) {
        super(genericRepository);
    }
}

package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemImageDTO;
import com.backend.elbuensabor.entities.ItemImage;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemImageMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.ItemImageRepository;
import com.backend.elbuensabor.services.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemImageServiceImpl extends GenericServiceImpl<ItemImage, ItemImageDTO, Long> implements ItemImageService {

    @Autowired
    private ItemImageRepository itemImageRepository;

    private final ItemImageMapper itemImageMapper = ItemImageMapper.getInstance();

    public ItemImageServiceImpl(GenericRepository<ItemImage, Long> genericRepository, GenericMapper<ItemImage, ItemImageDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }
}

package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemDTO;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, ItemDTO, Long> implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    private ItemMeasurementUnitRepository itemMeasurementUnitRepository;

    private final ItemMapper itemMapper = ItemMapper.getInstance();

    public ItemServiceImpl(GenericRepository<Item, Long> genericRepository, GenericMapper<Item, ItemDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception{
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> itemDTOs = new ArrayList<>();

            for (Item item : items) {
                ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitRepository.findByItemId(item.getId());
                ItemDTO itemDTO = itemMapper.toDTO(item);
                itemMapper.mapMeasurementUnitToDTO(itemDTO, item, itemMeasurementUnit);
                itemDTOs.add(itemDTO);
            }

            return itemDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ItemDTO getItem(Long id) throws Exception{
        try {
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
            ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitRepository.findByItemId(item.getId());
            ItemDTO itemDTO = itemMapper.toDTO(item);
            itemMapper.mapMeasurementUnitToDTO(itemDTO, item, itemMeasurementUnit);
            return itemDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Item saveItem(ItemDTO dto) throws Exception {
        try {
            Item item = itemMapper.toEntity(dto);

            if(dto.getCategoryId() != null) {
                if(categoryRepository.existsById(dto.getCategoryId())) {
                    Category category = categoryRepository.findById(dto.getCategoryId()).get();
                    item.setCategory(category);
                } else {
                    throw new Exception("La categoría no existe");
                }
            }

            if(dto.getItemTypeId() != null) {
                if(dto.getItemTypeId() != null) {
                    if(itemTypeRepository.existsById(dto.getItemTypeId())) {
                        ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId()).get();
                        item.setItemType(itemType);
                    } else {
                        throw new Exception("El tipo de item no existe");
                    }
                }
            }

            Item savedItem = itemRepository.save(item);

            ItemMeasurementUnit itemMeasurementUnit = itemMapper.mapMeasurementUnit(dto, savedItem, measurementUnitRepository);

            itemMeasurementUnitRepository.save(itemMeasurementUnit);

            return savedItem;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) throws Exception {
        try {
            // Buscar el item en la base de datos utilizando el ID proporcionado
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

            // Actualizar los atributos del item con los nuevos valores del ItemDTO
            item.setName(itemDTO.getName());
            item.setBlocked(itemDTO.getBlocked());

            if (itemDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(itemDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + itemDTO.getCategoryId()));
                item.setCategory(category);
            }

            if (itemDTO.getItemTypeId() != null) {
                ItemType itemType = itemTypeRepository.findById(itemDTO.getItemTypeId())
                        .orElseThrow(() -> new RuntimeException("ItemType not found with id: " + itemDTO.getItemTypeId()));
                item.setItemType(itemType);
            }

            // Guardar el item actualizado en la base de datos
            Item updatedItem = itemRepository.save(item);

            // Buscar y actualizar ItemMeasurementUnit
            ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitRepository.findByItemId(updatedItem.getId());
            if (itemDTO.getMeasurementUnitId() != null) {
                MeasurementUnit updatedMeasurementUnit = measurementUnitRepository.findById(itemDTO.getMeasurementUnitId())
                        .orElseThrow(() -> new RuntimeException("MeasurementUnit not found with id: " + itemDTO.getMeasurementUnitId()));
                itemMeasurementUnit.setMeasurementUnit(updatedMeasurementUnit);
                itemMeasurementUnitRepository.save(itemMeasurementUnit);
            }

            // Convertir el item actualizado a ItemDTO y devolverlo
            ItemDTO updatedItemDTOResult = itemMapper.toDTO(updatedItem);
            itemMapper.mapMeasurementUnitToDTO(updatedItemDTOResult, updatedItem, itemMeasurementUnit);

            return updatedItemDTOResult;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

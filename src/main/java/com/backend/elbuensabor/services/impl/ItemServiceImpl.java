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

import java.time.LocalDateTime;
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

    @Autowired
    private ItemCurrentStockRepository itemCurrentStockRepository;

    @Autowired
    private ItemCostPriceRepository itemCostPriceRepository;

    @Autowired
    private ItemStockConfigRepository itemStockConfigRepository;

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

                ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
                itemDTO.setCurrentStock(latestItemCurrentStock.getCurrentStock());

                ItemCostPrice latestItemCostPrice= itemCostPriceRepository.findLatestByItemId(item.getId());
                itemDTO.setCostPrice(latestItemCostPrice.getCostPrice());

                ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(item.getId());
                itemDTO.setMinStock(itemStockConfig.getMinStock());
                itemDTO.setMaxStock(itemStockConfig.getMaxStock());

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

            ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
            itemDTO.setCurrentStock(latestItemCurrentStock.getCurrentStock());

            ItemCostPrice latestItemCostPrice= itemCostPriceRepository.findLatestByItemId(item.getId());
            itemDTO.setCostPrice(latestItemCostPrice.getCostPrice());

            ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(item.getId());
            itemDTO.setMinStock(itemStockConfig.getMinStock());
            itemDTO.setMaxStock(itemStockConfig.getMaxStock());

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

            // Verificar si currentStock está presente en el DTO
            if (dto.getCurrentStock() != null) {
                // Crear una nueva instancia de ItemCurrentStock
                ItemCurrentStock itemCurrentStock = new ItemCurrentStock();
                itemCurrentStock.setCurrentStock(dto.getCurrentStock());
                itemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                itemCurrentStock.setItem(savedItem);

                // Guardar ItemCurrentStock en la base de datos
                itemCurrentStockRepository.save(itemCurrentStock);
            }

            // Verificar si costPrice está presente en el DTO
            if (dto.getCostPrice() != null) {
                // Crear una nueva instancia de ItemCostPrice
                ItemCostPrice itemCostPrice = new ItemCostPrice();
                itemCostPrice.setCostPrice(dto.getCostPrice());
                itemCostPrice.setCostPriceDate(LocalDateTime.now());
                itemCostPrice.setItem(savedItem);

                // Guardar ItemCurrentStock en la base de datos
                itemCostPriceRepository.save(itemCostPrice);
            }

            // Verificar si minStock y maxStock están presente en el DTO
            if (dto.getMinStock() != null && dto.getMaxStock() != null) {
                // Crear una nueva instancia de ItemStockConfig
                ItemStockConfig itemStockConfig = new ItemStockConfig();
                itemStockConfig.setMinStock(dto.getMinStock());
                itemStockConfig.setMaxStock(dto.getMaxStock());
                itemStockConfig.setItem(savedItem);

                // Guardar ItemStockConfig en la base de datos
                itemStockConfigRepository.save(itemStockConfig);
            }

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

            // Crear y guardar un nuevo registro de ItemCurrentStock si se proporciona un nuevo current_stock
            if (itemDTO.getCurrentStock() != null) {
                ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(updatedItem.getId());

                // Verificar si el nuevo current_stock es diferente del último registro en la base de datos
                if (latestItemCurrentStock == null || !latestItemCurrentStock.getCurrentStock().equals(itemDTO.getCurrentStock())) {
                    ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                    newItemCurrentStock.setCurrentStock(itemDTO.getCurrentStock());
                    newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                    newItemCurrentStock.setItem(updatedItem);
                    itemCurrentStockRepository.save(newItemCurrentStock);
                }
            }

            // Crear y guardar un nuevo registro de ItemCurrentStock si se proporciona un nuevo current_stock
            if (itemDTO.getCostPrice() != null) {
                ItemCostPrice latestItemCostPrice = itemCostPriceRepository.findLatestByItemId(updatedItem.getId());

                // Verificar si el nuevo current_stock es diferente del último registro en la base de datos
                if (latestItemCostPrice == null || !latestItemCostPrice.getCostPrice().equals(itemDTO.getCostPrice())) {
                    ItemCostPrice newItemCostPrice = new ItemCostPrice();
                    newItemCostPrice.setCostPrice(itemDTO.getCostPrice());
                    newItemCostPrice.setCostPriceDate(LocalDateTime.now());
                    newItemCostPrice.setItem(updatedItem);
                    itemCostPriceRepository.save(newItemCostPrice);
                }
            }

            // Verificar si minStock y maxStock están presente en el DTO
            if (itemDTO.getMinStock() != null && itemDTO.getMaxStock() != null) {
                // Crear una nueva instancia de ItemStockConfig
                ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(updatedItem.getId());
                itemStockConfig.setMinStock(itemDTO.getMinStock());
                itemStockConfig.setMaxStock(itemDTO.getMaxStock());
                itemStockConfig.setItem(updatedItem);

                // Guardar ItemStockConfig en la base de datos
                itemStockConfigRepository.save(itemStockConfig);
            }

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

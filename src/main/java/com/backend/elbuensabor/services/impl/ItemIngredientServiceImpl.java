package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemIngredientDTO;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.events.StockChangeEvent;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemIngredientMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.ItemIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemIngredientServiceImpl extends GenericServiceImpl<Item, ItemIngredientDTO, Long> implements ItemIngredientService {

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

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final ItemIngredientMapper itemIngredientMapper = ItemIngredientMapper.getInstance();

    public ItemIngredientServiceImpl(GenericRepository<Item, Long> genericRepository, GenericMapper<Item, ItemIngredientDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    public List<ItemIngredientDTO> getAllItemsIngredients() throws Exception {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemIngredientDTO> itemDTOs = new ArrayList<>();

            for (Item item : items) {
                if (item.getItemType().getId() == 1) {
                    ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitRepository.findByItemId(item.getId());
                    ItemIngredientDTO itemDTO = itemIngredientMapper.toDTO(item);

                    ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
                    itemDTO.setCurrentStock(latestItemCurrentStock.getCurrentStock());

                    ItemCostPrice latestItemCostPrice = itemCostPriceRepository.findLatestByItemId(item.getId());
                    itemDTO.setCostPrice(latestItemCostPrice.getCostPrice());

                    ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(item.getId());
                    itemDTO.setMinStock(itemStockConfig.getMinStock());
                    itemDTO.setMaxStock(itemStockConfig.getMaxStock());

                    itemIngredientMapper.mapMeasurementUnitToDTO(itemDTO, item, itemMeasurementUnit);
                    itemDTOs.add(itemDTO);
                }
            }

            return itemDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ItemIngredientDTO getItemIngredient(Long id) throws Exception{
        try {
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
            ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitRepository.findByItemId(item.getId());
            ItemIngredientDTO itemIngredientDTO = itemIngredientMapper.toDTO(item);
            itemIngredientMapper.mapMeasurementUnitToDTO(itemIngredientDTO, item, itemMeasurementUnit);

            ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
            itemIngredientDTO.setCurrentStock(latestItemCurrentStock.getCurrentStock());

            ItemCostPrice latestItemCostPrice= itemCostPriceRepository.findLatestByItemId(item.getId());
            itemIngredientDTO.setCostPrice(latestItemCostPrice.getCostPrice());

            ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(item.getId());
            itemIngredientDTO.setMinStock(itemStockConfig.getMinStock());
            itemIngredientDTO.setMaxStock(itemStockConfig.getMaxStock());

            return itemIngredientDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Item saveItemIngredient(ItemIngredientDTO dto) throws Exception {
        try {
            Item item = itemIngredientMapper.toEntity(dto);

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

            ItemMeasurementUnit itemMeasurementUnit = itemIngredientMapper.mapMeasurementUnit(dto, savedItem, measurementUnitRepository);

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
    public ItemIngredientDTO updateItemIngredient(Long id, ItemIngredientDTO itemIngredientDTO) throws Exception {
        try {
            // Buscar el item en la base de datos utilizando el ID proporcionado
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

            // Actualizar los atributos del item con los nuevos valores del ItemDTO
            item.setName(itemIngredientDTO.getName());
            item.setBlocked(itemIngredientDTO.getBlocked());

            if (itemIngredientDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(itemIngredientDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + itemIngredientDTO.getCategoryId()));
                item.setCategory(category);
            }

            if (itemIngredientDTO.getItemTypeId() != null) {
                ItemType itemType = itemTypeRepository.findById(itemIngredientDTO.getItemTypeId())
                        .orElseThrow(() -> new RuntimeException("ItemType not found with id: " + itemIngredientDTO.getItemTypeId()));
                item.setItemType(itemType);
            }

            // Guardar el item actualizado en la base de datos
            Item updatedItem = itemRepository.save(item);

            // Crear y guardar un nuevo registro de ItemCurrentStock si se proporciona un nuevo current_stock
            if (itemIngredientDTO.getCurrentStock() != null) {
                ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(updatedItem.getId());

                // Verificar si el nuevo current_stock es diferente del último registro en la base de datos
                if (latestItemCurrentStock == null || !latestItemCurrentStock.getCurrentStock().equals(itemIngredientDTO.getCurrentStock())) {
                    ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                    newItemCurrentStock.setCurrentStock(itemIngredientDTO.getCurrentStock());
                    newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                    newItemCurrentStock.setItem(updatedItem);
                    itemCurrentStockRepository.save(newItemCurrentStock);

                    eventPublisher.publishEvent(new StockChangeEvent(this, updatedItem.getId(), itemIngredientDTO.getCurrentStock()));
                }
            }

            // Crear y guardar un nuevo registro de ItemCurrentStock si se proporciona un nuevo cost_price
            if (itemIngredientDTO.getCostPrice() != null) {
                ItemCostPrice latestItemCostPrice = itemCostPriceRepository.findLatestByItemId(updatedItem.getId());

                // Verificar si el nuevo cost_price es diferente del último registro en la base de datos
                if (latestItemCostPrice == null || !latestItemCostPrice.getCostPrice().equals(itemIngredientDTO.getCostPrice())) {
                    ItemCostPrice newItemCostPrice = new ItemCostPrice();
                    newItemCostPrice.setCostPrice(itemIngredientDTO.getCostPrice());
                    newItemCostPrice.setCostPriceDate(LocalDateTime.now());
                    newItemCostPrice.setItem(updatedItem);
                    itemCostPriceRepository.save(newItemCostPrice);
                }
            }

            // Verificar si minStock y maxStock están presente en el DTO
            if (itemIngredientDTO.getMinStock() != null && itemIngredientDTO.getMaxStock() != null) {
                // Crear una nueva instancia de ItemStockConfig
                ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(updatedItem.getId());
                itemStockConfig.setMinStock(itemIngredientDTO.getMinStock());
                itemStockConfig.setMaxStock(itemIngredientDTO.getMaxStock());
                itemStockConfig.setItem(updatedItem);

                // Guardar ItemStockConfig en la base de datos
                itemStockConfigRepository.save(itemStockConfig);
            }

            // Buscar y actualizar ItemMeasurementUnit
            ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitRepository.findByItemId(updatedItem.getId());
            if (itemIngredientDTO.getMeasurementUnitId() != null) {
                MeasurementUnit updatedMeasurementUnit = measurementUnitRepository.findById(itemIngredientDTO.getMeasurementUnitId())
                        .orElseThrow(() -> new RuntimeException("MeasurementUnit not found with id: " + itemIngredientDTO.getMeasurementUnitId()));
                itemMeasurementUnit.setMeasurementUnit(updatedMeasurementUnit);
                itemMeasurementUnitRepository.save(itemMeasurementUnit);
            }

            // Convertir el item actualizado a ItemDTO y devolverlo
            ItemIngredientDTO updatedItemIngredientDTOResult = itemIngredientMapper.toDTO(updatedItem);
            itemIngredientMapper.mapMeasurementUnitToDTO(updatedItemIngredientDTOResult, updatedItem, itemMeasurementUnit);

            return updatedItemIngredientDTOResult;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Item blockUnlockItem(Long id, boolean blocked) throws Exception{
        try {
            Item item = itemRepository.findById(id).orElseThrow(() -> new Exception("Item not found"));
            item.setBlocked(blocked);
            return itemRepository.save(item);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

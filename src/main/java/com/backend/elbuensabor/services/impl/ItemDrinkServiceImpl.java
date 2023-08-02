package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.ItemDrinkDTO;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemDrinkMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.ItemDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDrinkServiceImpl extends GenericServiceImpl<Item, ItemDrinkDTO, Long> implements ItemDrinkService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private ItemDescriptionRepository itemDescriptionRepository;

    @Autowired
    ItemImageRepository itemImageRepository;

    @Autowired
    private ItemCostPriceRepository itemCostPriceRepository;

    @Autowired
    private ItemSellPriceRepository itemSellPriceRepository;

    @Autowired
    private ItemCurrentStockRepository itemCurrentStockRepository;

    @Autowired
    private ItemStockConfigRepository itemStockConfigRepository;

    public ItemDrinkServiceImpl(GenericRepository<Item, Long> genericRepository, GenericMapper<Item, ItemDrinkDTO> genericMapper){
        super(genericRepository, genericMapper);
    }

    private final ItemDrinkMapper itemDrinkMapper = ItemDrinkMapper.getInstance();

    @Override
    public List<ItemDrinkDTO> getAllDrinks() throws Exception {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDrinkDTO> itemDrinkDTOList = new ArrayList<>();

            for (Item item: items){
                if (item.getItemType().getId() == 3){

                    ItemDrinkDTO itemDrinkDTO = itemDrinkMapper.toDTO(item);

                    ItemDescription itemDescription = itemDescriptionRepository.findItemDesciptionByItemId(item.getId());
                    itemDrinkDTO.setDescription(itemDescription.getDescription());

                    ItemImage itemImage = itemImageRepository.findItemImageByItemId(item.getId());
                    itemDrinkDTO.setImage(itemImage.getImage());

                    ItemCostPrice latestItemCostPrice = itemCostPriceRepository.findLatestByItemId(item.getId());
                    itemDrinkDTO.setCostPrice(latestItemCostPrice.getCostPrice());

                    ItemSellPrice latestItemSellPrice = itemSellPriceRepository.findLatestByItemId(item.getId());
                    itemDrinkDTO.setSellPrice(latestItemSellPrice.getSellPrice());

                    ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
                    itemDrinkDTO.setCurrentStock(latestItemCurrentStock.getCurrentStock());

                    ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(item.getId());
                    itemDrinkDTO.setMinStock(itemStockConfig.getMinStock());
                    itemDrinkDTO.setMaxStock(itemStockConfig.getMaxStock());

                    itemDrinkDTOList.add(itemDrinkDTO);
                }
            }
            return itemDrinkDTOList;
        } catch (Exception e) {
            throw new Exception("Error al tratar de traer todas las Bebidas: " + e);
        }
    }

    @Override
    public ItemDrinkDTO getItemDrink(Long id) throws Exception {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado con el ID: " + id));

        ItemDrinkDTO itemDrinkDTO = itemDrinkMapper.toDTO(item);

        ItemDescription itemDescription = itemDescriptionRepository.findItemDesciptionByItemId(item.getId());
        itemDrinkDTO.setDescription(itemDescription.getDescription());

        ItemImage itemImage = itemImageRepository.findItemImageByItemId(item.getId());
        itemDrinkDTO.setImage(itemImage.getImage());

        ItemCostPrice latestItemCostPrice = itemCostPriceRepository.findLatestByItemId(item.getId());
        itemDrinkDTO.setCostPrice(latestItemCostPrice.getCostPrice());

        ItemSellPrice latestItemSellPrice = itemSellPriceRepository.findLatestByItemId(item.getId());
        itemDrinkDTO.setSellPrice(latestItemSellPrice.getSellPrice());

        ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
        itemDrinkDTO.setCurrentStock(latestItemCurrentStock.getCurrentStock());

        ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(item.getId());
        itemDrinkDTO.setMinStock(itemStockConfig.getMinStock());
        itemDrinkDTO.setMaxStock(itemStockConfig.getMaxStock());

        return itemDrinkDTO;
    }

    @Override
    @Transactional
    public Item saveDrink(ItemDrinkDTO dto) throws Exception {

        try {
            // Convertir DTO a entidad
            Item item = itemDrinkMapper.toEntity(dto);

            // Categoria
            if (dto.getCategoryId() != null) {
                if (categoryRepository.existsById(dto.getCategoryId())) {
                    Category category = categoryRepository.findById(dto.getCategoryId()).get();
                    item.setCategory(category);
                } else {
                    throw new Exception("La categoría no existe");
                }
            }

            // Tipo item == 3 ("Bebida")
            if (dto.getItemTypeId() != null) {
                if (itemTypeRepository.existsById(dto.getItemTypeId())) {
                    ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId()).get();
                    item.setItemType(itemType);
                } else {
                    throw new Exception("El tipo de ítem no existe");
                }
            }

            // Guardar el ítem en la base de datos
            Item savedItem = itemRepository.save(item);

            //Descripcion
            if(dto.getDescription() != null) {
                ItemDescription itemDescription = new ItemDescription();
                itemDescription.setDescription(dto.getDescription());
                itemDescription.setItem(savedItem);

                itemDescriptionRepository.save(itemDescription);
            }

            //Imagen
            if(dto.getImage() != null) {
                // Crear y relacionar el objeto ItemImage
                ItemImage itemImage = new ItemImage();
                itemImage.setImage(dto.getImage());
                itemImage.setItem(savedItem);

                itemImageRepository.save(itemImage);
            }

            //Cost Price
            if(dto.getCostPrice() != null) {
                ItemCostPrice itemCostPrice = new ItemCostPrice();
                itemCostPrice.setCostPrice(dto.getCostPrice());
                itemCostPrice.setCostPriceDate(LocalDateTime.now());
                itemCostPrice.setItem(savedItem);

                itemCostPriceRepository.save(itemCostPrice);
            }

            //Sell Price
            if(dto.getSellPrice() != null) {
                ItemSellPrice itemSellPrice = new ItemSellPrice();
                itemSellPrice.setSellPrice(dto.getSellPrice());
                itemSellPrice.setSellPriceDate(LocalDateTime.now());
                itemSellPrice.setItem(savedItem);

                itemSellPriceRepository.save(itemSellPrice);
            }

            //Current Stock
            if (dto.getCurrentStock() != null) {
                ItemCurrentStock itemCurrentStock = new ItemCurrentStock();
                itemCurrentStock.setCurrentStock(dto.getCurrentStock());
                itemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                itemCurrentStock.setItem(savedItem);

                itemCurrentStockRepository.save(itemCurrentStock);
            }

            //Stock Config
            if (dto.getMinStock() != null && dto.getMaxStock() != null) {
                ItemStockConfig itemStockConfig = new ItemStockConfig();
                itemStockConfig.setMinStock(dto.getMinStock());
                itemStockConfig.setMaxStock(dto.getMaxStock());
                itemStockConfig.setItem(savedItem);

                itemStockConfigRepository.save(itemStockConfig);
            }

            return  savedItem;

        } catch (Exception e) {
            throw new Exception("Error al guardar la bebida: ", e);
        }
    }

    @Override
    @Transactional
    public ItemDrinkDTO updateItemDrink(Long id, ItemDrinkDTO itemDrinkDTO) throws Exception {

        try {
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

            //Nombre y Status
            item.setName(itemDrinkDTO.getName());
            item.setBlocked(itemDrinkDTO.getBlocked());

            //Categoria
            if (itemDrinkDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(itemDrinkDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + itemDrinkDTO.getCategoryId()));
                item.setCategory(category);
            }

            //Item Type
            if (itemDrinkDTO.getItemTypeId() != null) {
                ItemType itemType = itemTypeRepository.findById(itemDrinkDTO.getItemTypeId())
                        .orElseThrow(() -> new RuntimeException("ItemType not found with id: " + itemDrinkDTO.getItemTypeId()));
                item.setItemType(itemType);
            }

            //Actualiza en la tabla Item
            Item updatedItem = itemRepository.save(item);

            //Descripcion
            if(itemDrinkDTO.getDescription() != null) {
                ItemDescription itemDescription = itemDescriptionRepository.findItemDesciptionByItemId(updatedItem.getId());

                if(itemDescription == null || !itemDescription.getDescription().equals(itemDrinkDTO.getDescription())){
                    itemDescription.setDescription(itemDrinkDTO.getDescription());
                    itemDescription.setItem(updatedItem);

                    itemDescriptionRepository.save(itemDescription);
                }
            }

            //Imagen
            if(itemDrinkDTO.getImage() != null) {
                ItemImage latestItemImage = itemImageRepository.findItemImageByItemId(updatedItem.getId());

                if(latestItemImage == null || !latestItemImage.getImage().equals(itemDrinkDTO.getImage())){
                    latestItemImage.setImage(itemDrinkDTO.getImage());
                    latestItemImage.setItem(updatedItem);
                    itemImageRepository.save(latestItemImage);
                }
            }

            //Cost Price
            if (itemDrinkDTO.getCostPrice() != null) {
                ItemCostPrice latestItemCostPrice = itemCostPriceRepository.findLatestByItemId(updatedItem.getId());

                if (latestItemCostPrice == null || !latestItemCostPrice.getCostPrice().equals(itemDrinkDTO.getCostPrice())) {
                    ItemCostPrice newItemCostPrice = new ItemCostPrice();
                    newItemCostPrice.setCostPrice(itemDrinkDTO.getCostPrice());
                    newItemCostPrice.setCostPriceDate(LocalDateTime.now());
                    newItemCostPrice.setItem(updatedItem);
                    itemCostPriceRepository.save(newItemCostPrice);
                }
            }

            //Sell Price
            if (itemDrinkDTO.getSellPrice() != null) {
                ItemSellPrice latestItemSellPrice = itemSellPriceRepository.findLatestByItemId(updatedItem.getId());

                if (latestItemSellPrice == null || !latestItemSellPrice.getSellPrice().equals(itemDrinkDTO.getSellPrice())) {
                    ItemSellPrice newItemSellPrice = new ItemSellPrice();
                    newItemSellPrice.setSellPrice(itemDrinkDTO.getSellPrice());
                    newItemSellPrice.setSellPriceDate(LocalDateTime.now());
                    newItemSellPrice.setItem(updatedItem);
                    itemSellPriceRepository.save(newItemSellPrice);
                }
            }

            //Current Stock
            if (itemDrinkDTO.getCurrentStock() != null) {
                ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(updatedItem.getId());

                if (latestItemCurrentStock == null || !latestItemCurrentStock.getCurrentStock().equals(itemDrinkDTO.getCurrentStock())) {
                    ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                    newItemCurrentStock.setCurrentStock(itemDrinkDTO.getCurrentStock());
                    newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                    newItemCurrentStock.setItem(updatedItem);
                    itemCurrentStockRepository.save(newItemCurrentStock);
                }
            }

            //Stock Config
            if (itemDrinkDTO.getMinStock() != null && itemDrinkDTO.getMaxStock() != null) {
                // Crear una nueva instancia de ItemStockConfig
                ItemStockConfig itemStockConfig = itemStockConfigRepository.findItemStockConfigByItemId(updatedItem.getId());
                itemStockConfig.setMinStock(itemDrinkDTO.getMinStock());
                itemStockConfig.setMaxStock(itemDrinkDTO.getMaxStock());
                itemStockConfig.setItem(updatedItem);

                itemStockConfigRepository.save(itemStockConfig);
            }

        } catch (Exception e) {
            throw new Exception("Error al actualizar el producto", e);
        }
        return null;
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

package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.IngredientDTO;
import com.backend.elbuensabor.DTO.ItemIngredientDTO;
import com.backend.elbuensabor.DTO.ItemProductDTO;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.ItemProductMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.ItemProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemProductServiceImpl extends GenericServiceImpl<Item, ItemProductDTO, Long> implements ItemProductService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeDetailRepository recipeDetailRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    private final ItemProductMapper itemProductMapper = ItemProductMapper.getInstance();

    public ItemProductServiceImpl(GenericRepository<Item, Long> genericRepository, GenericMapper<Item, ItemProductDTO> genericMapper){
        super(genericRepository, genericMapper);
    }

    public List<ItemProductDTO> getAllProducts() {
        List<Item> items = itemRepository.findAll();
        List<ItemProductDTO> itemProductDTOList = new ArrayList<>();

        for (Item item : items) {
            if (item.getItemType().getId() == 2) {
                Recipe recipe = recipeRepository.findByItemId(item.getId());
                List<RecipeDetail> recipeDetails = recipeDetailRepository.findByRecipeId(recipe.getId());

                recipe.setRecipeDetails(recipeDetails);

                ItemProductDTO itemProductDTO = itemProductMapper.toDTO(item);
                itemProductDTO.setRecipeDescription(recipe.getDescription());

                List<IngredientDTO> ingredientDTOList = new ArrayList<>();
                for (RecipeDetail recipeDetail : recipeDetails) {
                    IngredientDTO ingredientDTO = new IngredientDTO();
                    ingredientDTO.setIdIngredient(recipeDetail.getItem().getId());
                    ingredientDTO.setQuantity(recipeDetail.getQuantity());
                    ingredientDTOList.add(ingredientDTO);
                }
                itemProductDTO.setIngredients(ingredientDTOList);

                itemProductDTOList.add(itemProductDTO);
            }
        }

        return itemProductDTOList;
    }

    @Override
    public ItemProductDTO getItemProduct(Long id) throws Exception {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado con el ID: " + id));
        Recipe recipe = recipeRepository.findByItemId(id);

        List<RecipeDetail> recipeDetails = recipeDetailRepository.findByRecipeId(recipe.getId());
        recipe.setRecipeDetails(recipeDetails);

        ItemProductDTO itemProductDTO = itemProductMapper.toDTO(item);
        itemProductDTO.setRecipeDescription(recipe.getDescription());

        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (RecipeDetail recipeDetail : recipeDetails) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setIdIngredient(recipeDetail.getItem().getId());
            ingredientDTO.setQuantity(recipeDetail.getQuantity());
            ingredientDTOList.add(ingredientDTO);
        }
        itemProductDTO.setIngredients(ingredientDTOList);

        return itemProductDTO;
    }

    @Override
    @Transactional
    public Item saveProduct(ItemProductDTO dto) throws Exception {
        try {
            // Convertir DTO a entidad
            Item item = itemProductMapper.toEntity(dto);

            // Asignar categoría si está presente
            if (dto.getCategoryId() != null) {
                if (categoryRepository.existsById(dto.getCategoryId())) {
                    Category category = categoryRepository.findById(dto.getCategoryId()).get();
                    item.setCategory(category);
                } else {
                    throw new Exception("La categoría no existe");
                }
            }

            // Asignar tipo de ítem si está presente
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

            // Crear y guardar receta asociada al ítem
            Recipe recipe = new Recipe();
            recipe.setItem(item);
            recipe.setDescription(dto.getRecipeDescription());
            recipeRepository.save(recipe);

            // Crear lista para almacenar detalles de receta
            List<RecipeDetail> recipeDetails = new ArrayList<>();

            // Procesar y guardar detalles de receta
            for (IngredientDTO ingredientDTO : dto.getIngredients()) {
                RecipeDetail recipeDetail = new RecipeDetail();

                // Obtener y asignar ingrediente al detalle de receta
                try {
                    Optional<Item> itemIngredientOptional = itemRepository.findById(ingredientDTO.getIdIngredient());
                    if (itemIngredientOptional.isPresent()) {
                        Item itemIngredient = itemIngredientOptional.get();
                        recipeDetail.setItem(itemIngredient);
                    } else {
                        throw new Exception("El ingrediente con ID " + ingredientDTO.getIdIngredient() + " no existe");
                    }
                } catch (Exception e) {
                    throw new Exception("Error al obtener el ingrediente con ID " + ingredientDTO.getIdIngredient(), e);
                }

                // Asignar receta y cantidad al detalle de receta
                recipeDetail.setRecipe(recipe);
                recipeDetail.setQuantity(ingredientDTO.getQuantity());

                // Guardar detalle de receta en la base de datos
                try {
                    recipeDetailRepository.save(recipeDetail);
                } catch (Exception e) {
                    throw new Exception("Error al guardar el RecipeDetail", e);
                }

                // Agregar detalle de receta a la lista
                recipeDetails.add(recipeDetail);
            }

            // Asignar detalles de receta a la receta
            recipe.setRecipeDetails(recipeDetails);

            return savedItem;
        } catch (Exception e) {
            throw new Exception("Error al guardar el producto", e);
        }
    }

    @Override
    @Transactional
    public ItemProductDTO updateItemProduct(Long id, ItemProductDTO itemProductDTO) throws Exception {
        try {
            // Busca el producto en la base de datos usando el ID proporcionado
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

            // Actualiza los atributos del producto con los nuevos valores del ItemProductDTO
            item.setName(itemProductDTO.getName());
            item.setBlocked(itemProductDTO.getBlocked());

            // Si se proporciona un ID de categoría, busca la categoría y la asigna al producto
            if (itemProductDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(itemProductDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + itemProductDTO.getCategoryId()));
                item.setCategory(category);
            }

            // Si se proporciona un ID de tipo de producto, busca el tipo de producto y lo asigna al producto
            if (itemProductDTO.getItemTypeId() != null) {
                ItemType itemType = itemTypeRepository.findById(itemProductDTO.getItemTypeId())
                        .orElseThrow(() -> new RuntimeException("ItemType not found with id: " + itemProductDTO.getItemTypeId()));
                item.setItemType(itemType);
            }

            // Guarda el producto actualizado en la base de datos
            Item updatedItem = itemRepository.save(item);

            // Busca la receta existente asociada con el producto
            Recipe existingRecipe = recipeRepository.findByItemId(updatedItem.getId());

            // Si no existe una receta, crea una nueva y la asigna al producto
            if (existingRecipe == null) {
                existingRecipe = new Recipe();
                existingRecipe.setItem(updatedItem);
            }

            // Actualiza la descripción de la receta con el valor de ItemProductDTO
            existingRecipe.setDescription(itemProductDTO.getRecipeDescription());
            recipeRepository.save(existingRecipe);

            // Elimina todos los detalles de la receta existentes
            recipeDetailRepository.deleteAll(existingRecipe.getRecipeDetails());

            // Crea una nueva lista para almacenar los nuevos detalles de la receta
            List<RecipeDetail> newRecipeDetails = new ArrayList<>();

            // Itera a través de los ingredientes en el ItemProductDTO y crea nuevos detalles de la receta
            for (IngredientDTO ingredientDTO : itemProductDTO.getIngredients()) {
                RecipeDetail newRecipeDetail = new RecipeDetail();

                // Busca el ingrediente en la base de datos y lo asigna al detalle de la receta
                Item itemIngredient = itemRepository.findById(ingredientDTO.getIdIngredient())
                        .orElseThrow(() -> new Exception("El ingrediente con ID " + ingredientDTO.getIdIngredient() + " no existe"));
                newRecipeDetail.setItem(itemIngredient);

                // Asigna la receta existente y la cantidad al detalle de la receta
                newRecipeDetail.setRecipe(existingRecipe);
                newRecipeDetail.setQuantity(ingredientDTO.getQuantity());

                // Guarda el nuevo detalle de la receta en la base de datos
                recipeDetailRepository.save(newRecipeDetail);

                // Agrega el nuevo detalle de la receta a la lista
                newRecipeDetails.add(newRecipeDetail);
            }

            // Asigna los nuevos detalles de la receta a la receta existente
            existingRecipe.setRecipeDetails(newRecipeDetails);

            // Convierte el producto actualizado a un objeto ItemProductDTO y lo devuelve
            ItemProductDTO updatedItemProductDTOResult = itemProductMapper.toDTO(updatedItem);

            return updatedItemProductDTOResult;
        } catch (Exception e) {
            throw new Exception("Error al actualizar el producto", e);
        }
    }

}

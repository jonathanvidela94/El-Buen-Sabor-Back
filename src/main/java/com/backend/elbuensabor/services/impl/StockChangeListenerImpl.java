package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.Item;
import com.backend.elbuensabor.entities.ItemCurrentStock;
import com.backend.elbuensabor.entities.Recipe;
import com.backend.elbuensabor.entities.RecipeDetail;
import com.backend.elbuensabor.events.StockChangeEvent;
import com.backend.elbuensabor.repositories.ItemCurrentStockRepository;
import com.backend.elbuensabor.repositories.ItemRepository;
import com.backend.elbuensabor.repositories.RecipeDetailRepository;
import com.backend.elbuensabor.repositories.RecipeRepository;
import com.backend.elbuensabor.services.StockChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class StockChangeListenerImpl implements StockChangeListener {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCurrentStockRepository itemCurrentStockRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeDetailRepository recipeDetailRepository;

    @Override
    @EventListener
    public void handleStockChangeEvent(StockChangeEvent event) {

        Long idIngredient = event.getIngredientId();

        // Fetches all products that contain this ingredient
        List<Item> products = itemRepository.findProductsByIngredientId(idIngredient);

        for(Item product: products) {
            // Fetches the recipe associated with this product
            Recipe recipe = recipeRepository.findByItemId(product.getId());

            if(recipe != null) {
                // Fetches the details of the recipe
                List<RecipeDetail> recipeDetails = recipeDetailRepository.findByRecipeId(recipe.getId());

                // Calculates the minimum stock ratio
                int minStockRatio = Integer.MAX_VALUE;

                for(RecipeDetail recipeDetail: recipeDetails) {
                    int quantityIngredient = recipeDetail.getQuantity();
                    Item item = recipeDetail.getItem();
                    int currentStockIngredient = itemCurrentStockRepository.findLastCurrentStockByItemId(item.getId());
                    int stockRatio = currentStockIngredient / quantityIngredient;
                    minStockRatio = Math.min(minStockRatio, stockRatio);
                }

                // Fetches the current stock of the product
                int currentStockProduct = itemCurrentStockRepository.findLastCurrentStockByItemId(product.getId());

                // If the current product stock is not equal to the minimum stock ratio, a new current stock record is created
                if(currentStockProduct != minStockRatio) {
                    ItemCurrentStock itemCurrentStock = new ItemCurrentStock();
                    itemCurrentStock.setItem(product);
                    itemCurrentStock.setCurrentStock(minStockRatio);
                    itemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                    itemCurrentStockRepository.save(itemCurrentStock);
                }
            }
        }
    }
}

package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import com.backend.elbuensabor.entities.RecipeDetail;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO extends GenericDTO{
    private String description;
    private int preparationTime;
    private Item item;
    private List<RecipeDetail> recipeDetails;
}

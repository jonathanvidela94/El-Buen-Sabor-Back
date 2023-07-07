package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import com.backend.elbuensabor.entities.Recipe;
import lombok.Data;

@Data
public class RecipeDetailDTO extends GenericDTO{
    private Integer quantity;
    private Item item;
    private Recipe recipe;
}

package com.backend.elbuensabor.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ItemProductDTO extends GenericDTO{
    private String name;
    private Boolean blocked;
    private Long categoryId;
    private String categoryDenomination;
    private Long itemTypeId;
    private String description;
    private String recipeDescription;
    private List<IngredientDTO> ingredients;
    private String image;
    private Double sellPrice;
}

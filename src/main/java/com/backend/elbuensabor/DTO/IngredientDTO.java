package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class IngredientDTO {
    private Long id;
    private Integer quantity;
    private Integer currentStock;
}

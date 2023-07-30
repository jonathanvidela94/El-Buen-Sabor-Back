package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class ItemDrinkDTO extends GenericDTO{
    private String name;
    private Boolean blocked;
    private Long categoryId;
    private String categoryDenomination;
    private Long itemTypeId;
    private String description;
    private String image;
    private Integer currentStock;
    private Integer minStock;
    private Integer maxStock;
    private Double sellPrice;
    private Double costPrice;
}

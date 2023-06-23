package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class ItemDTO extends GenericDTO{
    private String name;
    private Boolean blocked;
    private Long categoryId;
    private String categoryDenomination;
    private Long itemTypeId;
    private String itemTypeDenomination;
    private Long measurementUnitId;
    private String measurementDenomination;
    private Integer currentStock;
    private Double costPrice;
    private Integer minStock;
    private Integer maxStock;
}

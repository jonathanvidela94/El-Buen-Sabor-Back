package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import lombok.Data;

@Data
public class ItemStockConfigDTO extends GenericDTO{
    private Integer minStock;
    private Integer maxStock;
    private Item item;
}

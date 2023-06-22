package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemCostPriceDTO extends GenericDTO{
    private Double costPrice;
    private LocalDateTime costPriceDate;
    private Item item;
}

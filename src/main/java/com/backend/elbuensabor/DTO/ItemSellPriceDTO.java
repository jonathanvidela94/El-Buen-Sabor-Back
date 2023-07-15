package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemSellPriceDTO extends GenericDTO{
    private Double sellPrice;
    private LocalDateTime sellPriceDate;
    private Item item;
}

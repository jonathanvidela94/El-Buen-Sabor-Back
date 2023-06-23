package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import lombok.Data;

import java.util.Date;

@Data
public class ItemCurrentStockDTO extends GenericDTO{
    private Integer currentStock;
    private Date currentStockDate;
    private Item item;
}

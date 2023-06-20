package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import lombok.Data;

@Data
public class ItemMeasurementUnitDTO extends GenericDTO{
    private Item item;
}

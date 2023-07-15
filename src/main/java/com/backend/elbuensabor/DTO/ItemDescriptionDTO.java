package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Item;
import lombok.Data;

@Data
public class ItemDescriptionDTO extends GenericDTO{
    private String description;
    private Item item;
}

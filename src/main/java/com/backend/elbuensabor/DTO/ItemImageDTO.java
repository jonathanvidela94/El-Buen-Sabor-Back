package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class ItemImageDTO extends GenericDTO{
    private String image;
    private Long itemId;
}

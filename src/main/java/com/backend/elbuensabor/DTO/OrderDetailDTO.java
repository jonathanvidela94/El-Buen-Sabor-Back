package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class OrderDetailDTO extends GenericDTO{
    private Integer quantity;
    private Double subtotal;
    private Long itemId;
}

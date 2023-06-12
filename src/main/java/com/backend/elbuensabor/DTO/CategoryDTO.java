package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class CategoryDTO extends GenericDTO{

    private String denomination;

    private Boolean isBanned;

    private Long categoryFatherId;

}

package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class CategoryDTO extends GenericDTO{

    private String denomination;

    private Boolean blocked;

    private Long categoryFatherId;

    private String categoryFatherDenomination;

}

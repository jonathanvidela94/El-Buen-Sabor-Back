package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.MeasurementUnit;
import lombok.Data;

@Data
public class MeasurementUnitDTO extends GenericDTO{
    private String denomination;
    private MeasurementUnit measurementUnit;
}

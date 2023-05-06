package com.backend.elbuensabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_measurement_unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemMeasurementUnit extends GenericEntity{
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_measurement_unit")
    private MeasurementUnit measurementUnit;
}

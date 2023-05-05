package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient extends GenericEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "min_stock")
    private Integer minStock;
    @Column(name = "max_stock")
    private Integer maxStock;
    @Column(name = "is_banned")
    private Boolean isBanned;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_measurement_unit")
    private MeasurementUnit measurementUnit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_category")
    private Category category;
}

package com.backend.elbuensabor.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "measurement_unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementUnit extends GenericEntity {
    @Column(name = "denomination")
    private String denomination;
}

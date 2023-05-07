package com.backend.elbuensabor.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemType extends GenericEntity{
    @Column(name = "denomination")
    private String denomination;
}

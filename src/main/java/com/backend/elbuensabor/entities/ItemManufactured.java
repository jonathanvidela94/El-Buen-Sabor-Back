package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_manufactured")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemManufactured extends GenericEntity{
    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

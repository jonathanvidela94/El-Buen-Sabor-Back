package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_drink")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDrink extends GenericEntity{
    @Column(name = "min_stock")
    private Integer minStock;
    @Column(name = "max_stock")
    private Integer maxStock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "item_drink_current_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDrinkCurrentStock extends GenericEntity{
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "date")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item_drink")
    private ItemDrink itemDrink;
}

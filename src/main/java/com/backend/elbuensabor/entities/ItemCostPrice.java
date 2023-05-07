package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "item_cost_price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCostPrice extends GenericEntity{
    @Column(name = "cost_price")
    private Double costPrice;
    @Column(name = "cost_price_date")
    private Date costPriceDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

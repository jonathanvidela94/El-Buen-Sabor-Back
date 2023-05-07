package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "item_sell_price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSellPrice extends GenericEntity{
    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "sell_price_date")
    private Date sellPriceDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

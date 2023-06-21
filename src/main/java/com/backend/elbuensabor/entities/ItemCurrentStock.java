package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_current_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCurrentStock extends GenericEntity{
    @Column(name = "current_stock")
    private Integer currentStock;
    @Column(name = "current_stock_date")
    private LocalDateTime currentStockDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

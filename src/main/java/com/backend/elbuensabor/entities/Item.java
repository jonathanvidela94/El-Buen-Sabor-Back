package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends GenericEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "blocked")
    private Boolean blocked;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_category")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item_type")
    private ItemType itemType;

}

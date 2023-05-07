package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemImage extends GenericEntity{
    @Column(name = "image")
    private String image;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

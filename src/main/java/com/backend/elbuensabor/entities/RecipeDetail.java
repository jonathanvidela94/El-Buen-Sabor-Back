package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipe_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetail extends GenericEntity{

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_recipe")
    private Recipe recipe;
}

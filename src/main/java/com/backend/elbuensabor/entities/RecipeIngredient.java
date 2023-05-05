package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipe_ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient extends GenericEntity{
    @Column(name = "quantity")
    private Integer quantity;

    //REVISAR ESTA RELACION :/
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_ingredient")
    private Ingredient ingredient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_recipe")
    private Recipe recipe;
}

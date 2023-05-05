package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ingredient_cost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCost extends GenericEntity{
    @Column(name = "cost")
    private Double cost;
    @Column(name = "date")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_ingredient")
    private Ingredient ingredient;
}

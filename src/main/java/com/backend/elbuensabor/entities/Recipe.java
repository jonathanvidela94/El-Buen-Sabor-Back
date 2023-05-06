package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends GenericEntity{
    @Column(name = "description")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeDetail> recipeDetails;
}

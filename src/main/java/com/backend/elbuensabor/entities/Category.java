package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends GenericEntity{
    @Column(name = "denomination")
    private String denomination;
    @Column(name = "is_banned")
    private Boolean isBaned;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFatherCategory")
    private Category fatherCategory;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> childCategories;
}

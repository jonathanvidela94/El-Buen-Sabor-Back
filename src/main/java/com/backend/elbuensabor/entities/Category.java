package com.backend.elbuensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "blocked")
    private Boolean blocked;

    @ManyToOne
    @JoinColumn(name = "idFatherCategory")
    @JsonBackReference
    private Category fatherCategory;

    @OneToMany(mappedBy = "fatherCategory", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Category> childCategories;
}
package com.backend.elbuensabor.entities;

import com.fasterxml.jackson.annotation.*;
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
    private Boolean isBanned;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFatherCategory")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category fatherCategory;

    @OneToMany(mappedBy = "fatherCategory", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Category> childCategories;

    @Transient
    @JsonProperty("fatherCategory")
    public Category getDirectFatherCategory() {
        return fatherCategory;
    }
}
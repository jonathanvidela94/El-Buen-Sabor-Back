package com.backend.elbuensabor.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends GenericEntity{
    @Column(name = "denomination")
    private String denomination;
    @Column(name = "is_item")
    private Boolean isItem;
    @Column(name = "is_banned")
    private Boolean isBaned;
}

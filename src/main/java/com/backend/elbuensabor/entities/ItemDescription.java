package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDescription extends GenericEntity{
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private Item item;
}

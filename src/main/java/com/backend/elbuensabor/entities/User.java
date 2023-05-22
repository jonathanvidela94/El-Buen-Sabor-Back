package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends GenericEntity{
    @Column(name = "auth0_id")
    private String auth0Id;
    @Column(name = "email")
    private String email;
    @Column(name = "blocked")
    private boolean blocked;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_role")
    private Role role;
}

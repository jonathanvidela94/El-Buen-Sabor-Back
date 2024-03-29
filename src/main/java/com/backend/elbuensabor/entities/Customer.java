package com.backend.elbuensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends GenericEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "apartment")
    private String apartment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonBackReference
    private List<Orders> orders;

}

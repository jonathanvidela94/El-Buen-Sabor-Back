package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Orders;
import com.backend.elbuensabor.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO extends GenericDTO {

    private String name;

    private String lastname;

    private String phone;

    private String address;

    private String apartment;

    private User user;

    private List<Orders> orders;

}

package com.backend.elbuensabor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth0RoleDTO {
    private String id;
    private String name;
    private String description;
}

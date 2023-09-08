package com.backend.elbuensabor.DTO;

import com.backend.elbuensabor.entities.Role;
import lombok.Data;

@Data
public class UserDTO extends GenericDTO{
    private String auth0Id;
    private String email;
    private boolean blocked;
    private boolean logged;
    private Role role;
}

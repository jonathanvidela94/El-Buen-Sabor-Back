package com.backend.elbuensabor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String userId;
    private boolean blocked;
    private List<RoleDTO> roles;
}

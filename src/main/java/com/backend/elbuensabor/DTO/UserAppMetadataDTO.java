package com.backend.elbuensabor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAppMetadataDTO {
    private List<RoleDTO> roles;
}
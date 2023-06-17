package com.backend.elbuensabor.DTO;

import lombok.Data;

@Data
public class RoleDTO extends GenericDTO{

    private String denomination;

    private String auth0RolId;

}

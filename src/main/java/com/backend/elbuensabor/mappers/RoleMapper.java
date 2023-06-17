package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.RoleDTO;
import com.backend.elbuensabor.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDTO> {

    static RoleMapper getInstance() {return Mappers.getMapper(RoleMapper.class);}

}

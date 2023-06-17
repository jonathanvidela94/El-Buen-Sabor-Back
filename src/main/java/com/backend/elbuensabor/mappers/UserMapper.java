package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.UserDTO;
import com.backend.elbuensabor.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDTO> {

    static UserMapper getInstance() { return Mappers.getMapper(UserMapper.class); }

}

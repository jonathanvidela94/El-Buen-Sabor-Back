package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.UserDTO;
import com.backend.elbuensabor.entities.User;

public interface UserService extends GenericService<User, UserDTO, Long>{
    User bloquearDesbloquearEmpleado(Long id, boolean blocked) throws Exception;

}

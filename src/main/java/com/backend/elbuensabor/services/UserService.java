package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.UserDTO;
import com.backend.elbuensabor.entities.User;

public interface UserService extends GenericService<User, UserDTO, Long>{
    User bloquearDesbloquearEmpleado(Long id, boolean blocked) throws Exception;
    boolean checkEmailExists(String email);
    void connected(String auth0) throws Exception;
    void disconnected(String auth0) throws Exception;
}

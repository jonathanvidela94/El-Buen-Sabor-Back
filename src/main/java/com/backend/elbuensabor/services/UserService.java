package com.backend.elbuensabor.services;

import com.backend.elbuensabor.entities.User;

public interface UserService extends GenericService<User,Long>{
    public User bloquearDesbloquearEmpleado(Long id, boolean blocked) throws Exception;
}

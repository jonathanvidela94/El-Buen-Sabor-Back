package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.User;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.UserRepository;
import com.backend.elbuensabor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {
    @Autowired
    private UserRepository roleRepository;

    public UserServiceImpl(GenericRepository<User, Long> genericRepository) {
        super(genericRepository);
    }
}

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
    private UserRepository userRepository;

    public UserServiceImpl(GenericRepository<User, Long> genericRepository) {
        super(genericRepository);
    }

    @Override
    public User bloquearDesbloquearEmpleado(Long id, boolean blocked) throws Exception {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
            user.setBlocked(blocked);
            return userRepository.save(user);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}

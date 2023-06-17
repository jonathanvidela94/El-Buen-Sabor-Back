
package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.UserDTO;
import com.backend.elbuensabor.entities.User;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.UserMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.UserRepository;
import com.backend.elbuensabor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserDTO, Long> implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.getInstance();

    public UserServiceImpl(GenericRepository<User, Long> genericRepository, GenericMapper<User, UserDTO> genericMapper) {
        super(genericRepository, genericMapper);
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

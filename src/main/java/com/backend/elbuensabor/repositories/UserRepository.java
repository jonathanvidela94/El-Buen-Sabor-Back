package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Long>{
    Optional<User> findByEmail(String email);
}

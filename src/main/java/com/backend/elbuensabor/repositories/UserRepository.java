package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long>{
}

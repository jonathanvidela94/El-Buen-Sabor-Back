package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Long>{
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.auth0Id = :auth0Id")
    Optional<User> findByAuth0Id(@Param("auth0Id") String auth0Id);
}

package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {
}

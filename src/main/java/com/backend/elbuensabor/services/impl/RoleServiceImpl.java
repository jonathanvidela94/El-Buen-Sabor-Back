package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.Role;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.RoleRepository;
import com.backend.elbuensabor.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl(GenericRepository<Role, Long> genericRepository) {
        super(genericRepository);
    }

    //Metodos que vienen de la interfaz...  D:
}

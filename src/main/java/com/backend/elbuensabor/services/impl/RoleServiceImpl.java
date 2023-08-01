package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.RoleDTO;
import com.backend.elbuensabor.entities.Role;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.RoleMapper;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.RoleRepository;
import com.backend.elbuensabor.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, RoleDTO, Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private final RoleMapper roleMapper = RoleMapper.getInstance();

    public RoleServiceImpl(GenericRepository<Role, Long> genericRepository, GenericMapper<Role, RoleDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

}

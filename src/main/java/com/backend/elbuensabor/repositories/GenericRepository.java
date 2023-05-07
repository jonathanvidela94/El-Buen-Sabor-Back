package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericRepository <E extends GenericEntity, ID extends Serializable> extends JpaRepository <E, ID> {
}

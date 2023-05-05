package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GenericRepository <E extends GenericEntity, ID extends Serializable> extends JpaRepository <E, ID> {
}

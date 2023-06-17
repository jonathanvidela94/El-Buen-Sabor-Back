package com.backend.elbuensabor.services;

import com.backend.elbuensabor.DTO.GenericDTO;
import com.backend.elbuensabor.entities.GenericEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericService <E extends GenericEntity, D extends GenericDTO, ID extends Serializable> {
    public List<D> findAll() throws Exception;
    public D findById(ID id) throws Exception;
    public E save(D dto) throws Exception;
    public E update(ID id, D dto) throws Exception;
    public void delete(ID id) throws Exception;
}
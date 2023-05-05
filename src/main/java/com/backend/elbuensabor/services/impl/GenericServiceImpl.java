package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.GenericEntity;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.services.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <E extends GenericEntity, ID extends Serializable> implements GenericService<E, ID>{
    protected GenericRepository<E, ID> genericRepository;

    public GenericServiceImpl(GenericRepository<E, ID> genericRepository){
        this.genericRepository = genericRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            List<E> entities = genericRepository.findAll();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entityOptional = genericRepository.findById(id);
            return entityOptional.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity = genericRepository.save(entity);
            return entity;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional= genericRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = genericRepository.save(entity);
            return entityUpdate;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (genericRepository.existsById(id)){
                genericRepository.deleteById(id);
            }
            return true;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

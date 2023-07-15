package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.GenericDTO;
import com.backend.elbuensabor.entities.GenericEntity;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.services.impl.repositories.GenericRepository;
import com.backend.elbuensabor.services.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <E extends GenericEntity, D extends GenericDTO, ID extends Serializable> implements GenericService<E, D, ID>{

    protected GenericRepository<E, ID> genericRepository;
    protected GenericMapper<E, D> genericMapper;

    public GenericServiceImpl(GenericRepository<E, ID> genericRepository, GenericMapper<E, D> genericMapper){
        this.genericRepository = genericRepository;
        this.genericMapper = genericMapper;
    }

    @Override
    public List<D> findAll() throws Exception {
        try{
            List<E> entities = genericRepository.findAll();
            return genericMapper.toDTOsList(entities);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public D findById(ID id) throws Exception {
        try{
            E entity = genericRepository.findById(id).get();
            return genericMapper.toDTO(entity);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(D dto) throws Exception {
        try{
            return genericRepository.save(genericMapper.toEntity(dto));
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, D dto) throws Exception {
        try {
            Optional<E> entityOptional = genericRepository.findById(id);

            if(entityOptional.isEmpty()) {
                throw new Exception("No se encontro la entidad a actualizar");
            }
            return genericRepository.save(genericMapper.toEntity(dto));
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(ID id) throws Exception {
        try {
            if (!genericRepository.existsById(id)){
                throw new Exception("No se encontro la entidad a eliminar");
            }
            genericRepository.deleteById(id);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

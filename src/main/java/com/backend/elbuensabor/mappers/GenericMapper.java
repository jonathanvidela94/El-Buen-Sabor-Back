package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.GenericDTO;
import com.backend.elbuensabor.entities.GenericEntity;

import java.util.List;

public interface GenericMapper <E extends GenericEntity, D extends GenericDTO> {
    D toDTO(E source);
    E toEntity(D source);
    List<D> toDTOsList(List<E> source);
    List<E> toEntitiesList(List<D> source);
}

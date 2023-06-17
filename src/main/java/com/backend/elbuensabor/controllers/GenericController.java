package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.GenericDTO;
import com.backend.elbuensabor.entities.GenericEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface GenericController <E extends GenericEntity, D extends GenericDTO, ID extends Serializable>{
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOneById(@PathVariable ID id);
    public ResponseEntity<?> save(@RequestBody D dto);
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody D dto);
    public ResponseEntity<?> delete(@PathVariable ID id);
}

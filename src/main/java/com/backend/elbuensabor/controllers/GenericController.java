package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.entities.GenericEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

public interface GenericController <E extends GenericEntity, ID extends Serializable>{
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOneById(@PathVariable ID id);
    public ResponseEntity<?> delete(@PathVariable ID id);
}

package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemIngredientDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Item;
import com.backend.elbuensabor.services.ItemIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ingredients")
public class ItemIngredientController extends GenericControllerImpl<Item, ItemIngredientDTO> {

    @Autowired
    private ItemIngredientService service;

    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @Override
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllItemsIngredients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getItemIngredient(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody ItemIngredientDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.saveItemIngredient(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemIngredientDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateItemIngredient(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<?> blockUnlockItem(@PathVariable Long id, @RequestParam boolean blocked) {
        try {
            Item item = service.blockUnlockItem(id, blocked);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

}

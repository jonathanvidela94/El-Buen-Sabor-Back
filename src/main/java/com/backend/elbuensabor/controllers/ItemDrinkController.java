package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.ItemDrinkDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Item;
import com.backend.elbuensabor.services.ItemDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/drinks")
public class ItemDrinkController extends GenericControllerImpl<Item, ItemDrinkDTO> {

    @Autowired
    private ItemDrinkService service;

    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @Override
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllDrinks());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getItemDrink(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody ItemDrinkDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.saveDrink(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemDrinkDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateItemDrink(id, dto));
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

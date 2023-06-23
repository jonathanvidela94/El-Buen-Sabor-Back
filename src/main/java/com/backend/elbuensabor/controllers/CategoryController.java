package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/categories")
public class CategoryController extends GenericControllerImpl<Category, CategoryDTO> {

    @Autowired
    private CategoryService service;
    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @Override
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody CategoryDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.saveCategory(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateCategory(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<?> blockUnlockCategory(@PathVariable Long id, @RequestParam boolean blocked) {
        try {
            Category category = service.blockUnlockCategory(id, blocked);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @GetMapping("/filter/{itemTypeId}")
    public ResponseEntity<?> findUnlockedCategories(@PathVariable Long itemTypeId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findUnlockedCategoriesByItemType(itemTypeId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @GetMapping("/filter/{itemTypeId}/{id}")
    public ResponseEntity<?> findUnlockedCategoriesExceptId(@PathVariable Long itemTypeId, @PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findUnlockedCategoriesByItemTypeExceptId(itemTypeId, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    //Fixing Categories front-end :D
    @GetMapping("/filter")
    public ResponseEntity<?> findUnlockedCategories(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findUnlockedCategories());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

}

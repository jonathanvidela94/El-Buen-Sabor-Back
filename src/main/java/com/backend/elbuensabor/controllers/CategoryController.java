package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Category;
import com.backend.elbuensabor.entities.User;
import com.backend.elbuensabor.services.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/categories")
public class CategoryController extends GenericControllerImpl<Category, CategoryServiceImpl> {

    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";
    @PostMapping("/with-parent/{parentId}")
    public ResponseEntity<?> saveCategoryWithParent(@RequestBody Category newCategory, @PathVariable Long parentId) {
        try {
            service.saveCategoryWithParent(newCategory, parentId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoría creada con éxito junto a su padre");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE);
        }
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<?> lockUnlockCategory(@PathVariable Long id, @RequestParam boolean blocked) {
        try {
            Category category = service.lockUnlockCategory(id, blocked);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

}

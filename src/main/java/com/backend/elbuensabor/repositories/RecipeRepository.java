package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends GenericRepository<Recipe, Long>{
}

package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends GenericRepository<Recipe, Long>{
    @Query(value = "SELECT * FROM recipe r WHERE r.fk_item = :itemId", nativeQuery = true)
    Recipe findByItemId(@Param("itemId") Long itemId);
}

package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.RecipeDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDetailRepository extends GenericRepository<RecipeDetail,Long> {
    @Query(value = "SELECT * FROM recipe_detail rd WHERE rd.fk_recipe = :recipeId", nativeQuery = true)
    List<RecipeDetail> findByRecipeId(@Param("recipeId") Long recipeId);
}

package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends GenericRepository<Category,Long>{
    @Query (value = "SELECT * FROM category WHERE blocked = false", nativeQuery = true)
    List<Category> findUnlockedCategories();
    @Query (value = "SELECT * FROM category WHERE blocked = false AND id != :id", nativeQuery = true)
    List<Category> findUnlockedCategoriesExceptId(@Param("id") Long id);
}

package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends GenericRepository<Category,Long>{
    @Query(value = "SELECT * FROM category WHERE blocked = false AND fk_item_type = :itemTypeId", nativeQuery = true)
    List<Category> findUnlockedCategoriesByItemType(@Param("itemTypeId") Long itemTypeId);
    @Query (value = "SELECT * FROM category WHERE blocked = false AND fk_item_type = :itemTypeId AND id != :id", nativeQuery = true)
    List<Category> findUnlockedCategoriesByItemTypeExceptId(@Param("itemTypeId") Long itemTypeId, @Param("id") Long id);

    //Fixing Categories front-end :D
    @Query (value = "SELECT * FROM category WHERE blocked = false", nativeQuery = true)
    List<Category> findUnlockedCategories();
}

package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends GenericRepository<Item, Long>{

    @Query(value = "SELECT DISTINCT i.* FROM item i " +
            "INNER JOIN item_type it ON i.fk_item_type = it.id " +
            "INNER JOIN recipe r ON i.id = r.fk_item " +
            "INNER JOIN recipe_detail rd ON r.id = rd.fk_recipe " +
            "WHERE it.id = 2 AND rd.fk_item = :itemId", nativeQuery = true)
    List<Item> findProductsByIngredientId(@Param("itemId") Long itemId);

}

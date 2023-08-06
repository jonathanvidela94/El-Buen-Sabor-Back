package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.ItemDescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDescriptionRepository extends GenericRepository<ItemDescription, Long>{
    @Query(value = "SELECT * FROM item_description WHERE fk_item = :itemId", nativeQuery = true)
    ItemDescription findItemDesciptionByItemId(@Param("itemId") Long itemId);
}

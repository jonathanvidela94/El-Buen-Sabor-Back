package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.ItemImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends GenericRepository<ItemImage, Long>{
    @Query(value = "SELECT * FROM item_image WHERE fk_item = :itemId", nativeQuery = true)
    ItemImage findItemImageByItemId(@Param("itemId") Long itemId);
}

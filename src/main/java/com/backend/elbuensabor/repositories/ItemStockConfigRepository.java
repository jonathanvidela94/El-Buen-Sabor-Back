package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.ItemStockConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStockConfigRepository extends GenericRepository<ItemStockConfig, Long>{
    @Query(value = "SELECT * FROM item_stock_config WHERE fk_item = :itemId", nativeQuery = true)
    ItemStockConfig findItemStockConfigByItemId(@Param("itemId") Long itemId);
}

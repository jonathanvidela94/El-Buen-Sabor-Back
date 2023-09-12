package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.ItemCurrentStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCurrentStockRepository extends GenericRepository<ItemCurrentStock, Long>{
    @Query(value = "SELECT * FROM item_current_stock i WHERE i.fk_item = :itemId ORDER BY i.current_stock_date DESC LIMIT 1", nativeQuery = true)
    ItemCurrentStock findLatestByItemId(@Param("itemId") Long itemId);
    @Query(value = "SELECT current_stock FROM item_current_stock " +
            "WHERE fk_item = :itemId " +
            "AND current_stock_date = (SELECT MAX(current_stock_date) " +
            "                          FROM item_current_stock " +
            "                          WHERE fk_item = :itemId) " +
            "ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Integer findLastCurrentStockByItemId(@Param("itemId") Long itemId);

}

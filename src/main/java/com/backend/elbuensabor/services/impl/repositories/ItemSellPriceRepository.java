package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.ItemSellPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSellPriceRepository extends GenericRepository<ItemSellPrice, Long>{
    @Query(value = "SELECT * FROM item_sell_price i WHERE i.fk_item = :itemId ORDER BY i.sell_price_date DESC LIMIT 1", nativeQuery = true)
    ItemSellPrice findLatestByItemId(@Param("itemId") Long itemId);
}

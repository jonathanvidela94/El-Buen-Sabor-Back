package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.ItemCostPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCostPriceRepository extends GenericRepository<ItemCostPrice, Long>{
    @Query(value = "SELECT * FROM item_cost_price i WHERE i.fk_item = :itemId ORDER BY i.cost_price_date DESC LIMIT 1", nativeQuery = true)
    ItemCostPrice findLatestByItemId(@Param("itemId") Long itemId);
}

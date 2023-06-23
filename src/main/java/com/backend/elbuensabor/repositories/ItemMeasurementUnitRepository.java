package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.ItemMeasurementUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMeasurementUnitRepository extends GenericRepository<ItemMeasurementUnit, Long>{

    @Query(value = "SELECT * FROM item_measurement_unit imu WHERE imu.fk_item = :itemId", nativeQuery = true)
    ItemMeasurementUnit findByItemId(@Param("itemId") Long itemId);

}

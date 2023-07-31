package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends GenericRepository<Item, Long>{
}

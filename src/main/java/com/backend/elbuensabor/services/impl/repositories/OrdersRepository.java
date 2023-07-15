package com.backend.elbuensabor.services.impl.repositories;

import com.backend.elbuensabor.entities.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends GenericRepository<Orders, Long>{
}

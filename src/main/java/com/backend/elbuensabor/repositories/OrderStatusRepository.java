package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends GenericRepository<OrderStatus, Long>{
}

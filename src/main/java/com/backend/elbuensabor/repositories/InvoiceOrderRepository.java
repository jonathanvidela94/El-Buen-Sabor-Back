package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.InvoiceOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceOrderRepository extends GenericRepository<InvoiceOrder,Long>{
}

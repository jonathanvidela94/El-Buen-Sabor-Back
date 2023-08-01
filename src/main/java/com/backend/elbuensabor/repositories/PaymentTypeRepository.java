package com.backend.elbuensabor.repositories;

import com.backend.elbuensabor.entities.PaymentType;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends GenericRepository<PaymentType, Long>{
}

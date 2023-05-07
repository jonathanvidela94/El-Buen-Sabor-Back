package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.entities.InvoiceOrder;
import com.backend.elbuensabor.repositories.GenericRepository;
import com.backend.elbuensabor.repositories.InvoiceOrderRepository;
import com.backend.elbuensabor.services.InvoiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceOrderServiceImpl extends GenericServiceImpl<InvoiceOrder,Long> implements InvoiceOrderService {
    @Autowired
    private InvoiceOrderRepository invoiceOrderRepository;

    public InvoiceOrderServiceImpl(GenericRepository<InvoiceOrder, Long> genericRepository) {
        super(genericRepository);
    }
}

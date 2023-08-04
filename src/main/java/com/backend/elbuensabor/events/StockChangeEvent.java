package com.backend.elbuensabor.events;

import org.springframework.context.ApplicationEvent;

public class StockChangeEvent extends ApplicationEvent {
    private Long id;
    private Integer currentStock;

    public StockChangeEvent(Object source, Long id, Integer currentStock) {
        super(source);
        this.id = id;
        this.currentStock = currentStock;
    }

    public Long getId() {
        return id;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

}

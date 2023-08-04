package com.backend.elbuensabor.services;

import com.backend.elbuensabor.events.StockChangeEvent;

public interface StockChangeListener {
    void handleStockChangeEvent(StockChangeEvent event);
}

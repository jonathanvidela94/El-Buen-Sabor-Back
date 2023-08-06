package com.backend.elbuensabor.events;

import org.springframework.context.ApplicationEvent;

public class StockChangeEvent extends ApplicationEvent {
    private final Long ingredientId;

    public StockChangeEvent(Object source, Long ingredientId) {
        super(source);
        this.ingredientId = ingredientId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

}

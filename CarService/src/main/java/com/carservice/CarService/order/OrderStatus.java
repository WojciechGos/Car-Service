package com.carservice.CarService.order;

import lombok.Getter;


public enum OrderStatus {
    CREATING(0),
    NEW(1),
    IN_PROGRESS(2),
    COMPLETED(3),
    CANCELLED(4);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package com.carservice.CarService.payment;

public enum PaymentStatus {
    INCOMPLETE,
    EXPIRED,
    FAILED,
    CANCELED,
    SUCCEEDED,
    REFUNDED,
    PARTIALLY_REFUNDED,
    PENDING,
    VOID,
    CHARGEBACK
}

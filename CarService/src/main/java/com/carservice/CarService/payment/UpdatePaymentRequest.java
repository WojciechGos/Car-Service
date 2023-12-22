package com.carservice.CarService.payment;

import java.math.BigDecimal;

public record UpdatePaymentRequest(
        BigDecimal amount,
        Long clientId,
        PaymentStatus paymentStatus
) {
}

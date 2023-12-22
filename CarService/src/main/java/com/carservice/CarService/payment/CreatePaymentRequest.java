package com.carservice.CarService.payment;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        BigDecimal amount,
        Long clientId
) {
}

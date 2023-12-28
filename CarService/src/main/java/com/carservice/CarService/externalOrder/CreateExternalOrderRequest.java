package com.carservice.CarService.externalOrder;

import java.math.BigDecimal;

public record CreateExternalOrderRequest (
    Long workerId,
    Long externalOrderId
    ){

}

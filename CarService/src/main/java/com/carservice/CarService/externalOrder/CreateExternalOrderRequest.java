package com.carservice.CarService.externalOrder;

import java.math.BigDecimal;

public record CreateExternalOrderRequest (
    Long workerId,
    Long externalOrderId,
    String itemName,
    BigDecimal price,
    Integer quantity,
    Long producerId,
    String wholesalerName
    ){

}

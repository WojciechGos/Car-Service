package com.carservice.CarService.externalOrder;

import java.math.BigDecimal;

public record AddItemToExternalOrderRequest (
        Long externalOrderId,
        String itemName,
        BigDecimal price,
        Integer quantity,
        Long producerId,
        String wholesalerName
){
}

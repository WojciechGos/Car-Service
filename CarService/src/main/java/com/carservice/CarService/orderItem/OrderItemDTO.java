package com.carservice.CarService.orderItem;

import java.math.BigDecimal;

public record OrderItemDTO (
    Long id,
    Long externalOrderItemId,
    String sparePartName,
    BigDecimal price,
    Integer quantity,
    Long producerId,
    String producerName,
    String wholesaler,
    String parameters
) {

}

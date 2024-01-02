package com.carservice.CarService.requestItem;

import java.math.BigDecimal;

public record RequestItemDTO (
      Long id,
      String producerName,
      String wholesaler,
      String parameters,
      String itemName,
      BigDecimal price,
      Integer quantity

)
{
}

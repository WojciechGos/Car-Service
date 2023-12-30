package com.carservice.CarService.OrderSparePart;

import java.math.BigDecimal;

public record OrderSparePartDTO (
        Long id,
        String sparePartName,
        Long sparePartId,
        BigDecimal price,
        Integer quantity,
        Long producerId,
        String producerName
){
}

package com.carservice.CarService.cost;

import com.carservice.CarService.OrderSparePart.OrderSparePartDTO;

import java.math.BigDecimal;
import java.util.List;

public record CostDTO(
        Long id,
        String name,
        List<OrderSparePartDTO> orderSparePartDTOS,
        BigDecimal laborPrice
) {
}

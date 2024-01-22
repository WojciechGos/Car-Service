package com.carservice.CarService.cost;

import java.math.BigDecimal;
import java.util.Map;

public record CostRequest(
        String name,
        Map<Long, Integer> sparePartQuantities,
        BigDecimal laborPrice,
        Long commissionId,
        String costType
) {
}

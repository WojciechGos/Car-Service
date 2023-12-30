package com.carservice.CarService.cost;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record CostRequest(
        String name,
        LocalDate createDate,
        Map<Long, Integer> sparePartQuantities,
        BigDecimal laborPrice,
        BigDecimal totalCost
) {
}

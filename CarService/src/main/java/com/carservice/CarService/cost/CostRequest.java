package com.carservice.CarService.cost;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CostRequest(
        String name,
        LocalDate createDate,
        List<Long> sparePartsIds,
        BigDecimal laborPrice,
        BigDecimal totalCost
) {
}

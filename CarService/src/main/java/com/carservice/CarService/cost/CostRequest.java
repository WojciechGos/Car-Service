package com.carservice.CarService.cost;

import com.carservice.CarService.commission.Commission;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record CostRequest(
        String name,
        Map<Long, Integer> sparePartQuantities,
        BigDecimal laborPrice,
        BigDecimal totalCost,
        Long commissionId,
        String costType
) {
}

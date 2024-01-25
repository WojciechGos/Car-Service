package com.carservice.CarService.localOrder;

import com.carservice.CarService.order.OrderStatus;

public record UpdateLocalOrderRequest(
        OrderStatus orderStatus,
        Long commissionId
) {

}
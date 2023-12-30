package com.carservice.CarService.externalOrder;

import com.carservice.CarService.order.OrderStatus;

public record UpdateExternalOrder (
        OrderStatus orderStatus
){
}

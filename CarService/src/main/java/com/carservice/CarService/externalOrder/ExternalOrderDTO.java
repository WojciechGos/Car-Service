package com.carservice.CarService.externalOrder;

import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ExternalOrderDTO (
        Long id,
        LocalDateTime createDate,
        LocalDateTime receiveDate,
        OrderStatus orderStatus,
        List<OrderItemDTO> items
)
{}

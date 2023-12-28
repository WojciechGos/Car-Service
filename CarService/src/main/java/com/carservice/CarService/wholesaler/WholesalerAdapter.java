package com.carservice.CarService.wholesaler;

import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.requestItem.RequestItemDTO;

import java.util.List;

public interface WholesalerAdapter {
    OrderItemDTO orderItem(Long id);
    List<OrderItemDTO> getOrderItemList();
}

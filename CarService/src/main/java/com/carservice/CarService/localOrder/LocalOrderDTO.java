package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePartDTO;
import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record LocalOrderDTO (
        Long id,
        LocalDateTime createDate,
        LocalDateTime receiveDate,
        OrderStatus orderStatus,
        List<Commission> commissionList,
        List<OrderSparePartDTO> orderSparePartList

){

}

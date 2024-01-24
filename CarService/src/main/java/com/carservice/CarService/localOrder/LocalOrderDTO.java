package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePartDTO;
import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.worker.Worker;

import java.time.LocalDateTime;
import java.util.List;

public record LocalOrderDTO (
        Long id,
        LocalDateTime createDate,
        LocalDateTime receiveDate,
        OrderStatus orderStatus,
        Commission commission,
        List<OrderSparePartDTO> orderSparePartList,
        Worker worker
){

}

package com.carservice.CarService.localOrder;


import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

public record CreateLocalOrderRequest (
        Long workerId,
        Long localOrderId,
        Integer quantity

){
    }

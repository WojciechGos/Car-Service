package com.carservice.CarService.localOrder;


import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

public record CreateLocalOrderRequest (
        String email,
        Integer quantity

){
    }

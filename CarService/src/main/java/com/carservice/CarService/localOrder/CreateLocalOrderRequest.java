package com.carservice.CarService.localOrder;

public record CreateLocalOrderRequest (
        String email,
        Integer quantity,
        Long commissionId
){
    }

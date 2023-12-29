package com.carservice.CarService.externalOrder;

public record CreateExternalOrderRequest(
    String email,
    Integer quantity
) {

}

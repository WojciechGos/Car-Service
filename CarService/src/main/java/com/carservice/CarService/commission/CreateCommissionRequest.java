package com.carservice.CarService.commission;


public record CreateCommissionRequest(
        Long vehicleId,
        Long clientId,
        Long workerId,
        String description
) {

}

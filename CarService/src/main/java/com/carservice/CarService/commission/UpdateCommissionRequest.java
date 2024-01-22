package com.carservice.CarService.commission;

public record UpdateCommissionRequest(
    Long vehicleId,
    Long clientId,
    Long workerId,
    String description,
    CommissionStatus commissionStatus
) {

}

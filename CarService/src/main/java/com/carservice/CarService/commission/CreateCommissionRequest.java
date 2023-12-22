package com.carservice.CarService.commission;


public record CreateCommissionRequest (
    Long vehicle_id,
    Long client_id,
    Long worker_id,
    String description
){

}

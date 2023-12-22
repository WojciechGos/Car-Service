package com.carservice.CarService.commission;


public record CreateCommissionRequest (
    Long vehicle_id,
    Long client_id,
    Long contractor_id,
    String description
){

}

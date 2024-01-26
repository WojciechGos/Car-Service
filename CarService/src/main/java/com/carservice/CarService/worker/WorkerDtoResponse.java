package com.carservice.CarService.worker;

public record WorkerDtoResponse(
        Long id,
        String name,
        String surname,
        String email,
        String role
) {

}

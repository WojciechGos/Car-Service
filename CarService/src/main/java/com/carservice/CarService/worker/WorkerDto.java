package com.carservice.CarService.worker;

public record WorkerDto(
        Long id,
        String name,
        String surname,
        String email
) {
}

package com.carservice.CarService.auth;

import com.carservice.CarService.worker.WorkerDto;

public record AuthenticationResponse(
        String token,
        WorkerDto workerDto
) {
}

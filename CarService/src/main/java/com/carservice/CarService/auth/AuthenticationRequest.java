package com.carservice.CarService.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}

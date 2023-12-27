package com.carservice.CarService.auth;

import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import com.carservice.CarService.jwt.JWTUtil;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Worker principal = (Worker) authentication.getPrincipal();
        WorkerDto workerDto = new WorkerDto(
                principal.getId(),
                principal.getName(),
                principal.getSurname(),
                principal.getEmail()
        );

        String token = jwtUtil.issueToken(
                principal.getEmail(),
                principal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
        return new AuthenticationResponse(token, workerDto);
    }

}
package com.carservice.CarService.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class WorkerUserDetailsService implements UserDetailsService {

    private final WorkerRepository workerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return workerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Email " + username + " not found"));
    }
}

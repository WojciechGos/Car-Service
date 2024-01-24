package com.carservice.CarService.methodsTests;
import com.carservice.CarService.worker.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkerUserDetailsServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerUserDetailsService workerUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername_existingUsername() {
        // Mock data
        String username = "test@example.com";
        Worker worker = new Worker();
        worker.setEmail(username);
        when(workerRepository.findByEmail(username)).thenReturn(Optional.of(worker));

        // Call the method
        UserDetails userDetails = workerUserDetailsService.loadUserByUsername(username);

        // Assertions
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_nonExistingUsername() {
        // Mock data
        String username = "nonexistent@example.com";
        when(workerRepository.findByEmail(username)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(UsernameNotFoundException.class, () -> workerUserDetailsService.loadUserByUsername(username));
    }
}

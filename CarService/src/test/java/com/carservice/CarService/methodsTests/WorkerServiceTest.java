package com.carservice.CarService.methodsTests;

import com.carservice.CarService.worker.*;
import com.carservice.CarService.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerService workerService;

    @Test
    void getWorkerEntityById_WhenWorkerExists() {
        Long workerId = 1L;
        Worker expectedWorker = new Worker();
        when(workerRepository.findById(workerId)).thenReturn(Optional.of(expectedWorker));

        Worker result = workerService.getWorkerEntityById(workerId);

        verify(workerRepository, times(1)).findById(workerId);
        assertNotNull(result, "Worker should not be null");
        assertEquals(expectedWorker, result, "Returned worker should match the expected worker");
    }

    @Test
    void getWorkerEntityById_WhenWorkerDoesNotExist() {
        Long workerId = 1L;
        when(workerRepository.findById(workerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> workerService.getWorkerEntityById(workerId),
            "Should throw ResourceNotFoundException when worker is not found");
        verify(workerRepository, times(1)).findById(workerId);
    }

    @Test
    void getWorkerByEmail_WhenWorkerExists() {
        String email = "karol@gmail.com";
        Worker expectedWorker = new Worker();
        when(workerRepository.findByEmail(email)).thenReturn(Optional.of(expectedWorker));

        Worker result = workerService.getWorkerByEmail(email);

        verify(workerRepository, times(1)).findByEmail(email);
        assertNotNull(result, "Worker should not be null");
        assertEquals(expectedWorker, result, "Returned worker should match the expected worker");
    }

    @Test
    void getWorkerByEmail_WhenWorkerDoesNotExist() {
        String email = "karol@gmail.com";
        when(workerRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> workerService.getWorkerByEmail(email),
            "Should throw ResourceNotFoundException when worker is not found");
        verify(workerRepository, times(1)).findByEmail(email);
    }
}

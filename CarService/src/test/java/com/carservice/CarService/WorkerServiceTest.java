package com.carservice.CarService;
import com.carservice.CarService.worker.*;
import com.carservice.CarService.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerService workerService;
/*
      @Test
       void getWorkerEntityById() {

           Long workerId = 1L;
           Worker worker = new Worker(1L);

           when(workerRepository.findById(anyLong())).thenReturn(Optional.of(worker));

           Worker result = workerService.getWorkerEntityById(workerId);

           verify(workerRepository, times(1)).findById(workerId);
           assertNotNull(result, "Worker nie powinien być null");
           assertEquals(workerId, result.getId(), "Identyfikator workera nie zgadza się");
       }
 */
    @Test
    void getWorkerEntityByIdThrowsException() {

        Long nonExistingWorkerId = 2L;

        when(workerRepository.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> workerService.getWorkerEntityById(nonExistingWorkerId),
            "Oczekiwano ResourceNotFoundException dla nieistniejącego workera");
    }


}

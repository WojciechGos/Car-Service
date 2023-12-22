package com.carservice.CarService.worker;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.vehicles.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public Worker getWorkerEntityById(Long workerId){
        return workerRepository.findById(workerId).orElseThrow(()-> new ResourceNotFoundException("Vehicle with id [%s] not found".formatted(workerId)));
    }
}

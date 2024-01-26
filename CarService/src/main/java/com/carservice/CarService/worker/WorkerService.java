package com.carservice.CarService.worker;

import com.carservice.CarService.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    public Worker getWorkerEntityById(Long workerId){
        return workerRepository.findById(workerId)
                .orElseThrow(()-> new ResourceNotFoundException("Worker with id [%s] not found".formatted(workerId)));
    }

    public Worker getWorkerByEmail(String email){
        return workerRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Worker with email [%s] not found".formatted(email)));
    }

    public List<WorkerDtoResponse> getAllWorkers(){
        List<Worker> workers = workerRepository.findAll();
        System.out.println(workers);
        return workers.stream().map(workerMapper::map).collect(Collectors.toList());
//        return workers;
    }

}

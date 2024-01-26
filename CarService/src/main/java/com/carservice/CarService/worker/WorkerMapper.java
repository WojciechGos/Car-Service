package com.carservice.CarService.worker;

import org.springframework.stereotype.Service;

@Service
public class WorkerMapper {
    public WorkerDtoResponse map(final Worker worker){
        return new WorkerDtoResponse(
                worker.getId(),
                worker.getName(),
                worker.getSurname(),
                worker.getEmail(),
                worker.getWorkerRole().toString()
        );
    }

}

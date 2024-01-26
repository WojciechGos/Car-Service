package com.carservice.CarService.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/workers")
public class WorkerController {

    private final WorkerService workerService;
    @GetMapping
    public ResponseEntity<List<WorkerDtoResponse>> getWorkers(){
        List<WorkerDtoResponse> workerDtoResponses = workerService.getAllWorkers();
        return new ResponseEntity<>(workerDtoResponses, HttpStatus.OK);

    }

}

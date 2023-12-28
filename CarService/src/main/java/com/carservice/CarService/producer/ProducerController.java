package com.carservice.CarService.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/producers")
public class ProducerController {
    private final ProducerService producerService;
    @GetMapping
    public ResponseEntity<List<Producer>> getAllProducers() {
        List<Producer> producers = producerService.getAllProducers();
        return new ResponseEntity<>(producers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable("id") Long producerId) {
        Producer producer = producerService.getProducerById(producerId);
        return new ResponseEntity<>(producer, HttpStatus.OK);
    }
}

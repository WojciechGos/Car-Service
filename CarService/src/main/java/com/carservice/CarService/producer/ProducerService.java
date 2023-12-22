package com.carservice.CarService.producer;

import com.carservice.CarService.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProducerService {
    private final ProducerRepository producerRepository;

    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    public Producer getProducerById(Long producerId) {
        return producerRepository.findById(producerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producer with id [%s] not found.".formatted(producerId)
                ));
    }
}

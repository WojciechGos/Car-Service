package com.carservice.CarService.producer;

import com.carservice.CarService.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Producer> getProducerByName(String name){
        System.out.println("name: " + name + " mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        Optional<Producer> producer = producerRepository.findByName(name);

//        System.out.println("getProducerByName: " + producer.get().getName() );

        return producer;
    }
}

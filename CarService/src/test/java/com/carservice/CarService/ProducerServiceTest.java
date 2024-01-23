package com.carservice.CarService;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerRepository;
import com.carservice.CarService.producer.ProducerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProducerServiceTest {

    @Test
    void getAllProducers() {
        ProducerRepository producerRepository = Mockito.mock(ProducerRepository.class);
        ProducerService producerService = new ProducerService(producerRepository);

        List<Producer> expectedProducers = Arrays.asList(
            new Producer("SpareGenius"),
            new Producer("TechSolve")
        );

        when(producerRepository.findAll()).thenReturn(expectedProducers);

        List<Producer> actualProducers = producerService.getAllProducers();

        assertEquals(expectedProducers, actualProducers);
        verify(producerRepository, times(1)).findAll();
    }

    @Test
    void getProducerById() {
        ProducerRepository producerRepository = Mockito.mock(ProducerRepository.class);
        ProducerService producerService = new ProducerService(producerRepository);

        Long producerId = 1L;
        Producer expectedProducer = new Producer("SpareGenius");
        expectedProducer.setId(producerId);

        when(producerRepository.findById(producerId)).thenReturn(Optional.of(expectedProducer));

        Producer actualProducer = producerService.getProducerById(producerId);

        assertEquals(expectedProducer, actualProducer);
        verify(producerRepository, times(1)).findById(producerId);
    }

    @Test
    void getProducerById_NotFound() {
        ProducerRepository producerRepository = Mockito.mock(ProducerRepository.class);
        ProducerService producerService = new ProducerService(producerRepository);

        Long producerId = 1L;

        when(producerRepository.findById(producerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> producerService.getProducerById(producerId));
        verify(producerRepository, times(1)).findById(producerId);
    }
}

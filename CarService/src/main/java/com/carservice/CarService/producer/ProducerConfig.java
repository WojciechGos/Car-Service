package com.carservice.CarService.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class ProducerConfig {
    /*@Bean
    @Order(value = 1)
    CommandLineRunner commandLineRunner(ProducerRepository producerRepository){
        return args -> {
            Producer spareGenius = new Producer(
                    "SpareGenius"
            );
            Producer smartFix = new Producer(
                    "SmartFix"
            );
            Producer techSolve = new Producer(
                    "TechSolve"
            );
            Producer innovateSpare = new Producer(
                    "InnovateSpare"
            );

            producerRepository.saveAll(
                    List.of(spareGenius, smartFix, techSolve, innovateSpare)
            );
        };
    }*/
}

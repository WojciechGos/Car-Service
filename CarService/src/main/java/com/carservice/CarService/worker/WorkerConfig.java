package com.carservice.CarService.worker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class WorkerConfig {
    /*@Bean
    @Order(value = 2)
    CommandLineRunner commandLineRunner(WorkerRepository workerRepository){
        return args -> {
            Worker jan = new Worker(
                    "Jan",
                    "Marzec",
                    "jan@gmail.com",
                    "555666777",
                    new BigDecimal("20.50"),
                    WorkerRole.RECEPTIONIST
            );
            Worker piotr = new Worker(
                    "Piotr",
                    "Czerwiec",
                    "piotr@gmail.com",
                    "111567345",
                    new BigDecimal("27.50"),
                    WorkerRole.MANAGER
            );
            Worker karol = new Worker(
                    "Karol",
                    "Lipiec",
                    "karol@gmail.com",
                    "333445554",
                    new BigDecimal("25.50"),
                    WorkerRole.CONTRACTOR
            );
            Worker maciej = new Worker(
                    "Maciej",
                    "Listopad",
                    "maciej@gmail.com",
                    "231453675",
                    new BigDecimal("24.50"),
                    WorkerRole.WAREHOUSEMAN
            );

            workerRepository.saveAll(
                    List.of(jan, piotr, karol, maciej)
            );
        };
    }*/
}

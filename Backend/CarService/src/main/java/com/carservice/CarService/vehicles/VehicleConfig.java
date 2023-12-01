package com.carservice.CarService.vehicles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class VehicleConfig {

    @Bean
    CommandLineRunner commandLineRunner(VehicleRepository repository){
        return args -> {
            Vehicle vehicle1 = new Vehicle();

            repository.saveAll(
                    List.of(vehicle1)
            );
        };
    }
}

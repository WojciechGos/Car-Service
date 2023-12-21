package com.carservice.CarService.vehicles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.List;

@Configuration
public class VehicleConfig {

    @Bean
    CommandLineRunner commandLineRunner(VehicleRepository repository){
        return args -> {
            Vehicle vehicle1 = new Vehicle("opel", "astra", "123123", "TKI 5VF5","2001");
            Vehicle vehicle2 = new Vehicle("bmw", "idk", "1231234", "KLI 6039C", "2007");

            repository.saveAll(
                    List.of(vehicle1, vehicle2)
            );
        };
    }
}

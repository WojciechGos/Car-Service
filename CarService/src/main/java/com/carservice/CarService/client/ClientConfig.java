package com.carservice.CarService.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {
    /*@Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository){
        return args -> {
            Client wojtek = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
            Client alex = new Client("Alex", "Maj", "alex@gmail.com", "555432897");

            clientRepository.saveAll(
                    List.of(wojtek, alex)
            );
        };
    }*/
}

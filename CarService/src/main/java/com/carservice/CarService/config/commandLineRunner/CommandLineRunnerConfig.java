package com.carservice.CarService.config.commandLineRunner;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientRepository;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerRepository;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleRepository;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerRepository;
import com.carservice.CarService.worker.WorkerRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class CommandLineRunnerConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            ClientRepository clientRepository,
            ProducerRepository producerRepository,
            WorkerRepository workerRepository,
            VehicleRepository vehicleRepository
            ){
        return args -> {
            addClients(clientRepository);
            addProducers(producerRepository);
            addVehicles(vehicleRepository);
            addWorkers(workerRepository);
        };
    }

    private void addClients(ClientRepository clientRepository) {
        Client wojtek = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
        Client alex = new Client("Alex", "Maj", "alex@gmail.com", "555432897");

        clientRepository.saveAll(
                List.of(wojtek, alex)
        );
    }

    private void addVehicles(VehicleRepository vehicleRepository) {
        Vehicle vehicle1 = new Vehicle("opel", "astra", "123123", "TKI 5VF5","2001");
        Vehicle vehicle2 = new Vehicle("bmw", "idk", "1231234", "KLI 6039C", "2007");

        vehicleRepository.saveAll(
                List.of(vehicle1, vehicle2)
        );
    }

    private void addProducers(ProducerRepository producerRepository) {
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
    }

    private void addWorkers(WorkerRepository workerRepository) {
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
    }
}

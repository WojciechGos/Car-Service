package com.carservice.CarService.config.commandLineRunner;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientRepository;
import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionBuilder;
import com.carservice.CarService.commission.CommissionRepository;
import com.carservice.CarService.cost.Cost;
import com.carservice.CarService.cost.CostRepository;
import com.carservice.CarService.payment.Payment;
import com.carservice.CarService.payment.PaymentRepository;
import com.carservice.CarService.payment.PaymentStatus;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerRepository;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartRepository;
import com.carservice.CarService.sparePart.SparePartState;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleRepository;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerRepository;
import com.carservice.CarService.worker.WorkerRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class CommandLineRunnerConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            ClientRepository clientRepository,
            ProducerRepository producerRepository,
            WorkerRepository workerRepository,
            VehicleRepository vehicleRepository,
            SparePartRepository sparePartRepository,
            CostRepository costRepository,
            CommissionRepository commissionRepository,
            PaymentRepository paymentRepository
            ){
        return args -> {
            addClients(clientRepository);
            addProducers(producerRepository);
            addVehicles(vehicleRepository);
            addWorkers(workerRepository);
            addSpareParts(sparePartRepository, producerRepository);
            addCosts(costRepository, sparePartRepository);
            addCommissions(commissionRepository, vehicleRepository, clientRepository, workerRepository);
            addPayments(paymentRepository, clientRepository);
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

    private void addSpareParts(SparePartRepository sparePartRepository, ProducerRepository producerRepository) {
        SparePart brakePad = new SparePart(
                "Brake Pad",
                new BigDecimal("30.00"),
                10,
                producerRepository.findById(1L).orElse(null),
                SparePartState.WHOLE
        );
        SparePart oilFilter = new SparePart(
                "Oil Filter",
                new BigDecimal("8.50"),
                20,
                producerRepository.findById(2L).orElse(null),
                SparePartState.DAMAGED
        );
        SparePart sparkPlug = new SparePart(
                "Spark Plug",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                SparePartState.MISS
        );

        sparePartRepository.saveAll(
                List.of(brakePad, oilFilter, sparkPlug)
        );
    }

    private void addCosts(
            CostRepository costRepository,
            SparePartRepository sparePartRepository
    ) {
        List<SparePart> spareParts = sparePartRepository.findAll();

        Cost cost1 = new Cost(
                "Cost 1",
                LocalDate.now(),
                spareParts.subList(0, 2),
                new BigDecimal("50.00"),
                new BigDecimal("0.00")
        );

        Cost cost2 = new Cost(
                "Cost 2",
                LocalDate.now(),
                spareParts.subList(2, 3),
                new BigDecimal("30.00"),
                new BigDecimal("0.00")
        );

        costRepository.saveAll(List.of(cost1, cost2));
    }

    public void addCommissions(
            CommissionRepository commissionRepository,
            VehicleRepository vehicleRepository,
            ClientRepository clientRepository,
            WorkerRepository workerRepository
    ) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        List<Worker> workers = workerRepository.findAll();

        Commission commission1 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(0))
                .buildClient(clients.get(0))
                .buildWorker(workers.get(0))
                .buildDescription("Commission 1")
                .build();

        Commission commission2 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(1))
                .buildClient(clients.get(1))
                .buildWorker(workers.get(1))
                .buildDescription("Commission 2")
                .build();

        commissionRepository.saveAll(List.of(commission1, commission2));
    }

    private void addPayments(
            PaymentRepository paymentRepository,
            ClientRepository clientRepository
    ) {
        List<Client> clients = clientRepository.findAll();

        Payment payment1 = new Payment();
        payment1.setAmount(new BigDecimal("50.00"));
        payment1.setClient(clients.get(0));
        payment1.setPaymentStatus(PaymentStatus.PENDING);

        Payment payment2 = new Payment();
        payment2.setAmount(new BigDecimal("30.00"));
        payment2.setClient(clients.get(1));
        payment2.setPaymentStatus(PaymentStatus.EXPIRED);

        paymentRepository.saveAll(List.of(payment1, payment2));
    }
}

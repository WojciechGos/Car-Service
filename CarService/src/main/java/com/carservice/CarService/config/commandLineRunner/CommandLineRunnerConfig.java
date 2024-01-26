package com.carservice.CarService.config.commandLineRunner;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartRepository;
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
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleRepository;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerRepository;
import com.carservice.CarService.worker.WorkerRole;
import com.carservice.CarService.localOrder.LocalOrder;
import com.carservice.CarService.localOrder.LocalOrderRepository;
import com.carservice.CarService.orderItem.OrderItemRepository;
import com.carservice.CarService.externalOrder.ExternalOrderRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            PaymentRepository paymentRepository,
            PasswordEncoder passwordEncoder,
            OrderSparePartRepository orderSparePartRepository,
            OrderItemRepository orderItemRepository,
            LocalOrderRepository localOrderRepository,
            ExternalOrderRepository externalOrderRepository
            ){
        return args -> {
            addClients(clientRepository);
            addProducers(producerRepository);
            addVehicles(vehicleRepository);
            addWorkers(workerRepository, passwordEncoder);
            addSpareParts(sparePartRepository, producerRepository);
            addCosts(costRepository, sparePartRepository, orderSparePartRepository);
            addCommissions(commissionRepository, vehicleRepository, clientRepository, workerRepository, costRepository);
            addPayments(paymentRepository, clientRepository);
            addLocalOrder(workerRepository, localOrderRepository, commissionRepository, sparePartRepository, orderSparePartRepository);
        };
    }
    private void addLocalOrder(
            WorkerRepository workerRepository,
            LocalOrderRepository localOrderRepository,
            CommissionRepository commissionRepository,
            SparePartRepository sparePartRepository,
            OrderSparePartRepository orderSparePartRepository
    )
    {
        List<Worker> worker = workerRepository.findAll();
        LocalDateTime createDate = LocalDateTime.now();

        LocalOrder localOrder1 = new LocalOrder(worker.get(0), createDate);

        LocalOrder localOrder2 = new LocalOrder(worker.get(3), createDate);
        localOrderRepository.saveAll(
            List.of(localOrder1, localOrder2)
        );

        List<Commission> commissions = commissionRepository.findAll();

        localOrder1.setCommission(commissions.get(0));

        List<SparePart> sparePartList = sparePartRepository.findAll();
        List<OrderSparePart> orderSparePartList = new ArrayList<>();
        OrderSparePart orderSparePart1 = orderSparePartRepository.save(new OrderSparePart(
                sparePartList.get(0),
                5
        ));
        orderSparePartList.add(orderSparePart1);
        localOrder1.setItems(orderSparePartList);

        localOrder2.setCommission(commissions.get(1));
        List<OrderSparePart> orderSparePartList2 = new ArrayList<>();
        OrderSparePart orderSparePart2 = orderSparePartRepository.save(new OrderSparePart(
                sparePartList.get(1),
                2
        ));
        orderSparePartList2.add(orderSparePart2);
        localOrder2.setItems(orderSparePartList2);

        localOrderRepository.saveAll(List.of(localOrder1, localOrder2));
    }


    private void addClients(ClientRepository clientRepository) {
        Client wojtek = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
        Client alex = new Client("Alex", "Maj", "alex@gmail.com", "555432897");
        Client john = new Client("John", "Doe", "john@gmail.com", "123456789");
        Client emily = new Client("Emily", "Smith", "emily@gmail.com", "987654321");
        Client anna = new Client("Anna", "Johnson", "anna@gmail.com", "456789012");
        Client robert = new Client("Robert", "Brown", "robert@gmail.com", "876543210");
        Client maria = new Client("Maria", "Lee", "maria@gmail.com", "321098765");
        Client james = new Client("James", "Taylor", "james@gmail.com", "654321098");
        Client lisa = new Client("Lisa", "Anderson", "lisa@gmail.com", "890123456");
        Client michael = new Client("Michael", "White", "michael@gmail.com", "234567890");

        clientRepository.saveAll(
                List.of(wojtek, alex, john, emily, anna, robert, maria, james, lisa, michael)
        );
    }


    private void addVehicles(VehicleRepository vehicleRepository) {
        Vehicle vehicle1 = new Vehicle("opel", "astra", "123123", "TKI 5VF5","2001");
        Vehicle vehicle2 = new Vehicle("bmw", "idk", "1231234", "KLI 6039C", "2007");
        Vehicle vehicle3 = new Vehicle("audi", "a3", "123456", "WRO 7890X", "2015");
        Vehicle vehicle4 = new Vehicle("mercedes", "c-class", "789012", "GDA 1234Y", "2020");
        Vehicle vehicle5 = new Vehicle("ford", "focus", "567890", "POZ 4321R", "2018");
        Vehicle vehicle6 = new Vehicle("toyota", "corolla", "123789", "GDN 5678Z", "2019");
        Vehicle vehicle7 = new Vehicle("honda", "civic", "456123", "WAW 8765K", "2016");
        Vehicle vehicle8 = new Vehicle("volkswagen", "golf", "789456", "KRK 2345M", "2022");

        vehicleRepository.saveAll(
                List.of(vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7, vehicle8)
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
        Producer KNECHT = new Producer(
                        "KNECHT"
        );
        Producer BOSCH = new Producer(
                        "BOSCH"
        );
        Producer DENSO = new Producer(
                        "DENSO"
        );
        Producer MANNFILTER = new Producer(
                        "MANN-FILTER"
        );
        Producer LUK = new Producer(
                        "LUK"
        );
        Producer VALEO = new Producer(
                        "VALEO"
        );
        Producer SACHS = new Producer(
                        "SACHS"
        );
        Producer AISIN = new Producer(
                        "AISIN"
        );
        Producer ATE = new Producer(
                        "ATE"
        );
        Producer EBCBrakes = new Producer(
                        "EBC Brakes"
        );
        Producer TRW = new Producer(
                        "TRW"
        );
        Producer Ferodo = new Producer(
                        "Ferodo"
        );
        Producer BEHR = new Producer(
                        "BEHR"
        );
        Producer NRF = new Producer(
                        "NRF"
        );

        producerRepository.saveAll(
                List.of(spareGenius, smartFix, techSolve, innovateSpare, KNECHT, BOSCH,DENSO, MANNFILTER, LUK, VALEO,SACHS,AISIN,ATE,EBCBrakes,TRW,Ferodo,BEHR,NRF)
        );
    }

    private void addWorkers(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        Worker jan = new Worker(
                "Jan",
                "Marzec",
                "jan@gmail.com",
                "555666777",
                new BigDecimal("20.50"),
                WorkerRole.ROLE_RECEPTIONIST,
                passwordEncoder.encode("password1")
        );
        Worker piotr = new Worker(
                "Piotr",
                "Czerwiec",
                "piotr@gmail.com",
                "111567345",
                new BigDecimal("27.50"),
                WorkerRole.ROLE_MANAGER,
                passwordEncoder.encode("password2")
        );
        Worker karol = new Worker(
                "Karol",
                "Lipiec",
                "karol@gmail.com",
                "333445554",
                new BigDecimal("25.50"),
                WorkerRole.ROLE_CONTRACTOR,
                passwordEncoder.encode("password3")
        );
        Worker maciej = new Worker(
                "Maciej",
                "Listopad",
                "maciej@gmail.com",
                "231453675",
                new BigDecimal("24.50"),
                WorkerRole.ROLE_WAREHOUSEMAN,
                passwordEncoder.encode("password4")
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
                "Producent\tBRAKE-MAX\n" +
                        "Indeks\tBMX123\n" +
                        "Kod EAN\t1234567890123\n" +
                        "Typ klocka\tStandardowy\n" +
                        "Grubość [mm]\t15\n" +
                        "Materiał\tKeramika"
        );
        SparePart oilFilter = new SparePart(
                "Oil Filter",
                new BigDecimal("8.50"),
                10,
                producerRepository.findById(2L).orElse(null),
                """
                        Producent\tXYZ\n
                        Indeks\tABC123\n
                        Kod EAN\t1234567890123\n
                        Typ filtra\tFiltr oleju\n
                        Wysokość [mm]\t90\n
                        Wysokość 2 [mm]\t85,5
                        """
        );
        SparePart sparkPlug = new SparePart(
                "Spark Plug",
                new BigDecimal("4.00"),
                10,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\tIGNITE-PLUS\n
                        Indeks\tIP123\n
                        Kod EAN\t1234567890123\n
                        Typ świecy\tStandardowa\n
                        Rozmiar gwintu\tM14x1.25\n
                        Elektroda odstająca [mm]\t0,8
                        """
        );
        SparePart airFilter = new SparePart(
                "Air filter",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\tACME\n
                        Indeks\tXY789\n
                        Kod EAN\t9876543210987\n
                        Typ filtra\tFiltr powietrza\n
                        Wysokość [mm]\t150\n
                        Wysokość 2 [mm]\t145,2
                        """
        );

        SparePart sparePart1 = new SparePart(
                "Engine",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\tECOBOOST\n
                        Numer modelu\tEB456\n
                        Typ silnika\tBenzynowy\n
                        Pojemność skokowa [cm³]\t1600\n
                        Moc [kW]\t120\n
                        Maksymalny moment obrotowy [Nm]\t200
                        """
        );
        SparePart sparePart2 = new SparePart(
                "Tire",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\tTIRE-MAX\n
                        Indeks opony\tTM123\n
                        Rozmiar\t205/55R16\n
                        Typ opony\tLetnia\n
                        Indeks prędkości\tH\n
                        Indeks nośności\t91
                        """
        );

        sparePartRepository.saveAll(
                List.of(brakePad, oilFilter, sparkPlug, airFilter, sparePart1, sparePart2)
        );
    }

    private void addCosts(
            CostRepository costRepository,
            SparePartRepository sparePartRepository,
            OrderSparePartRepository orderSparePartRepository
    ) {
        List<SparePart> spareParts = sparePartRepository.findAll();

        OrderSparePart orderSparePart1 = new OrderSparePart(spareParts.get(0), 5);
        OrderSparePart orderSparePart2 = new OrderSparePart(spareParts.get(1), 7);
        OrderSparePart orderSparePart3 = new OrderSparePart(spareParts.get(2), 3);
        OrderSparePart orderSparePart4 = new OrderSparePart(spareParts.get(3), 10);

        orderSparePartRepository.saveAll(List.of(orderSparePart1, orderSparePart2, orderSparePart3, orderSparePart4));

        List<OrderSparePart> orderSpareParts = orderSparePartRepository.findAll();

        List<Cost> costs = List.of(
                new Cost(
                        "Cost 1",
                        LocalDateTime.now(),
                        orderSpareParts.subList(1, 2),
                        new BigDecimal("50.00")
                ),
                new Cost(
                        "Cost 2",
                        LocalDateTime.now(),
                        orderSpareParts.subList(2, 3),
                        new BigDecimal("30.00")
                )
        );

        costRepository.saveAll(costs);
    }

    public void addCommissions(
            CommissionRepository commissionRepository,
            VehicleRepository vehicleRepository,
            ClientRepository clientRepository,
            WorkerRepository workerRepository,
            CostRepository costRepository
    ) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        List<Worker> workers = workerRepository.findAll();
        List<Cost> costs = costRepository.findAll();

        Commission commission1 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(0))
                .buildClient(clients.get(0))
                .buildWorker(workers.get(2))
                .buildDescription("Przegląd techniczny i wymiana oleju. Zlecenie obejmuje kompleksowy przegląd techniczny pojazdu. W ramach usługi planowana jest również wymiana oleju silnikowego oraz sprawdzenie ogólnej kondycji pojazdu.")
                .build();

        Commission commission2 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(1))
                .buildClient(clients.get(1))
                .buildWorker(workers.get(2))
                .buildDescription("Naprawa układu hamulcowego. "
                        + "Klient zgłosił problem z układem hamulcowym w swoim samochodzie. Zlecenie obejmuje kompleksową diagnostykę i naprawę układu hamulcowego, w tym wymianę zużytych elementów.")
                .build();

        Commission commission3 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(2))
                .buildClient(clients.get(2))
                .buildWorker(workers.get(2))
                .buildDescription("Wymiana rozrządu i płynu chłodzącego. "
                        + "Zlecenie obejmuje kompleksową wymianę rozrządu oraz płynu chłodzącego w samochodzie. Ponadto, planowana jest wymiana płynu chłodzącego dla utrzymania optymalnej temperatury pracy silnika.")
                .build();

        Commission commission4 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(3))
                .buildClient(clients.get(3))
                .buildWorker(workers.get(2))
                .buildDescription("Diagnostyka i naprawa systemu elektrycznego. "
                        + "Klient zgłosił problemy z systemem elektrycznym w swoim samochodzie. Zlecenie obejmuje kompleksową diagnostykę usterki oraz naprawę systemu elektrycznego.")
                .build();

        Commission commission5 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(4))
                .buildClient(clients.get(4))
                .buildWorker(workers.get(2))
                .buildDescription("Wymiana sprzęgła. "
                        + "Zlecenie obejmuje wymianę sprzęgła w samochodzie.")
                .build();

        Commission commission6 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(5))
                .buildClient(clients.get(5))
                .buildWorker(workers.get(2))
                .buildDescription("Serwis klimatyzacji. "
                        + "Klient zdecydował się na przegląd i serwis klimatyzacji w swoim samochodzie marki Toyota Corolla z 2019 roku. Zlecenie obejmuje sprawdzenie wydajności klimatyzacji, ewentualne doładowanie czynnika chłodzącego oraz przegląd filtrów.")
                .build();

        commissionRepository.saveAll(List.of(commission1, commission2, commission3, commission4, commission5, commission6));
        commission1.setTotalCost(costs.get(0));
        commission1.setCostEstimate(costs.get(1));
        commissionRepository.saveAll(List.of(commission1));
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

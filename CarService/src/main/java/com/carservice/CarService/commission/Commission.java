package com.carservice.CarService.commission;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Commission {
    @SequenceGenerator(
            name = "commission_sequence",
            sequenceName = "commission_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "commission_sequence"
    )
    private Long id;
    private LocalDate createDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker contractor;
    private String description;
}

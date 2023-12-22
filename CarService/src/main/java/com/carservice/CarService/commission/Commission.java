package com.carservice.CarService.commission;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.cost.Cost;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime createDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "workerId", nullable = false)
    private Worker contractor;
    private String description;
    @Enumerated(EnumType.STRING)
    private CommissionStatus commissionStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cost_estimate_id")
    private Cost costEstimate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "total_cost_id")
    private Cost totalCost;
}

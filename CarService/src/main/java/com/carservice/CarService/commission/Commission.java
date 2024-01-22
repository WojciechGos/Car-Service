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
            name = "commission_new_sequence",
            sequenceName = "commission_new_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "commission_new_sequence"
    )
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "workerId")
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

    @Override
    public String toString() {
        return "Commission{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", endDate=" + endDate +
                ", vehicle=" + vehicle +
                ", client=" + client +
                ", contractor=" + contractor +
                ", description='" + description + '\'' +
                ", commissionStatus=" + commissionStatus +
                ", costEstimate=" + costEstimate +
                ", totalCost=" + totalCost +
                '}';
    }
}

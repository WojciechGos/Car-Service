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
public class CommissionBuilder {
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

    public static CommissionBuilder getBase(){

        return new CommissionBuilder();
    }
    public CommissionBuilder buildVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        return this;
    }
    public CommissionBuilder buildClient(Client client){
        this.client = client;
        return this;
    }
    public CommissionBuilder buildWorker(Worker contractor){
        this.contractor = contractor;
        return this;
    }
    public CommissionBuilder buildDescription(String description){
        this.description = description;
        return this;
    }
    public Commission build(){
        Commission commission = new Commission();
        commission.setCreateDate(LocalDate.now());
        commission.setVehicle(vehicle);
        commission.setClient(client);
        commission.setContractor(contractor);

        return commission;

    }
}

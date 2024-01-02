package com.carservice.CarService.commission;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.worker.Worker;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommissionBuilder {

    private LocalDateTime createDate;
    private LocalDateTime endDate;
    private Vehicle vehicle;
    private Client client;
    private Worker contractor;
    private String description;
    private CommissionStatus commissionStatus;



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
        commission.setCreateDate(LocalDateTime.now());
        commission.setVehicle(vehicle);
        commission.setClient(client);
        commission.setContractor(contractor);
        commission.setCommissionStatus(CommissionStatus.PENDING);

        return commission;
    }
}

package com.carservice.CarService.worker;

import com.carservice.CarService.personalData.PersonalData;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
public class Worker extends PersonalData {
    @SequenceGenerator(
            name = "worker_sequence",
            sequenceName = "worker_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "worker_sequence"
    )
    private Long id;
    private BigDecimal salaryPerHour;
    private WorkerRole workerRole;

    public Worker(
            String name,
            String surname,
            String email,
            String phoneNumber,
            BigDecimal salaryPerHour,
            WorkerRole workerRole
    ) {
        super(name, surname, email, phoneNumber);
        this.salaryPerHour = salaryPerHour;
        this.workerRole = workerRole;
    }
}

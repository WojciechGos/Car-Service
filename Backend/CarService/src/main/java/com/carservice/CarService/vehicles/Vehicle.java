package com.carservice.CarService.vehicles;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Vehicle {
    @Id
    @SequenceGenerator(
            name="vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    private Long id;
    private String brand;
    private String model;
    private String vin;
    private String registrationNumber;
    private Date registrationDate;

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, String model, String vin, String registrationNumber, Date registrationDate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
    }



}

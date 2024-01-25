package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.commission.*;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.worker.Worker;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommissionBuilderTest {
    @Test
    void buildCommissionWithAllDetails() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        Client client = new Client();
        Worker worker = new Worker();
        String description = "Commission 1";

        // Act
        Commission commission = CommissionBuilder.getBase()
            .buildVehicle(vehicle)
            .buildClient(client)
            .buildWorker(worker)
            .buildDescription(description)
            .build();

        // Assert
        assertNotNull(commission);
        assertEquals(LocalDateTime.now().getYear(), commission.getCreateDate().getYear()); // Assuming you want to check the year
        assertEquals(vehicle, commission.getVehicle());
        assertEquals(client, commission.getClient());
        assertEquals(worker, commission.getContractor());
        assertEquals(CommissionStatus.PENDING, commission.getCommissionStatus());
        assertEquals(description, commission.getDescription());
    }

    @Test
    void buildCommissionWithMinimumDetails() {
        // Arrange
        // Minimal details for commission creation

        // Act
        Commission commission = CommissionBuilder.getBase().build();

        // Assert
        assertNotNull(commission);
        assertEquals(LocalDateTime.now().getYear(), commission.getCreateDate().getYear()); // Assuming you want to check the year
        // Check other default values or leave them as default in the CommissionBuilder
    }
}

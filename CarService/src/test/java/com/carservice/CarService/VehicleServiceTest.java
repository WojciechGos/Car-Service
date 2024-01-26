package com.carservice.CarService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleRepository;
import com.carservice.CarService.vehicles.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void getAllVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();
        when(vehicleRepository.findAll()).thenReturn(vehicles);


        List<Vehicle> result = vehicleService.getAllVehicles();


        verify(vehicleRepository, times(1)).findAll();
        assertEquals(vehicles, result);
    }

    @Test
    void createVehicle() {
        VehicleRepository vehicleRepository = Mockito.mock(VehicleRepository.class);
        VehicleService vehicleService = new VehicleService(vehicleRepository);

        Vehicle vehicle1 = new Vehicle("opel", "astra", "123123", "TKI 5VF5", "2001");
        Vehicle vehicle2 = new Vehicle("bmw", "idk", "993311", "KLI 6039C ", "2007");

        Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicle1);
        Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicle2);

        Long createdVehicleId1 = vehicleService.createVehicle(vehicle1);
        Long createVehicleId2 = vehicleService.createVehicle(vehicle2);

        assertEquals(vehicle1.getId(), createdVehicleId1);
        assertEquals(vehicle2.getId(), createVehicleId2);


    }

    @Test
    void updateVehicle() {

        Long vehicleId = 1L;
        Vehicle existingVehicle = new Vehicle("opel", "astra", "123123", "TKI 5VF5", "2001");
        existingVehicle.setId(vehicleId);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(existingVehicle);

        Vehicle updatedVehicle = new Vehicle("Updated Brand", "Updated Model", "Updated Vin", "Updated RegNum", "Updated Year");


        vehicleService.updateVehicle(vehicleId, updatedVehicle);


        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, times(1)).save(existingVehicle);

        assertEquals("Updated Brand", existingVehicle.getBrand());
        assertEquals("Updated Model", existingVehicle.getModel());
        assertEquals("Updated Vin", existingVehicle.getVin());
        assertEquals("Updated RegNum", existingVehicle.getRegistrationNumber());
        assertEquals("Updated Year", existingVehicle.getRegistrationDate());
    }

    @Test
    void updateVehicle_WhenVehicleNotFound_ShouldThrowException() {

        Long vehicleId = 1L;
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        Vehicle updatedVehicle = new Vehicle("Updated Brand", "Updated Model", "Updated Vin", "Updated RegNum", "Updated Year");


        assertThrows(ResourceNotFoundException.class, () -> vehicleService.updateVehicle(vehicleId, updatedVehicle));
        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, never()).save(any(Vehicle.class));
    }

    @Test
    void deleteVehicle() {

        Long vehicleId = 1L;

        vehicleService.deleteVehicle(vehicleId);

        verify(vehicleRepository, times(1)).deleteById(vehicleId);
    }

    @Test
    void getVehicleEntityById() {

        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle("opel", "astra", "123123", "TKI 5VF5", "2001");
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        Vehicle result = vehicleService.getVehicleEntityById(vehicleId);

        verify(vehicleRepository, times(1)).findById(vehicleId);
        assertEquals(vehicle, result);
    }

    @Test
    void getVehicleEntityById_WhenVehicleNotFound_ShouldThrowException() {

        Long vehicleId = 1L;
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.getVehicleEntityById(vehicleId));
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }


}

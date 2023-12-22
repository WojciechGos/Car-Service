package com.carservice.CarService.vehicles;

import com.carservice.CarService.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Long createVehicle(Vehicle vehicle){
        Vehicle createdVehicle = vehicleRepository.save(vehicle);
        return createdVehicle.getId();
    }

    public void updateVehicle(Long vehicleId, Vehicle vehicle){
        Vehicle updatedVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Vehicle with id [%s] not found".formatted(vehicleId)
                ));
        if(vehicle.getBrand() != null)
            updatedVehicle.setBrand(vehicle.getBrand());
        if(vehicle.getModel() != null)
            updatedVehicle.setModel(vehicle.getModel());
        if(vehicle.getVin() != null)
            updatedVehicle.setVin(vehicle.getVin());
        if(vehicle.getRegistrationNumber() != null)
            updatedVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
        if(vehicle.getRegistrationDate() != null)
            updatedVehicle.setRegistrationDate(vehicle.getRegistrationDate());

        vehicleRepository.save(updatedVehicle);
    }

    public void deleteVehicle(Long vehicleId ) {
        vehicleRepository.deleteById(vehicleId);
    }

    public Vehicle getVehicleEntityById(Long vehicleId){
        return vehicleRepository.findById(vehicleId).orElseThrow(()-> new ResourceNotFoundException("Vehicle with id [%s] not found".formatted(vehicleId)));
    }


}

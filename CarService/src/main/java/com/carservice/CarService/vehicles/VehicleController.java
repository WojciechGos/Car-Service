package com.carservice.CarService.vehicles;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles(){
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createVehicle(@RequestBody Vehicle vehicle){
        Long savedVehiclesId = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(savedVehiclesId, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateVehicle(
            @PathVariable("id") long vehicleId,
            @RequestBody Vehicle vehicle
    ){
        vehicleService.updateVehicle(vehicleId, vehicle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable("id") Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

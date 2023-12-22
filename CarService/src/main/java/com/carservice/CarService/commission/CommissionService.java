package com.carservice.CarService.commission;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientService;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleService;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final VehicleService vehicleService;
    private final ClientService clientService;
    private final WorkerService workerService;

    public List<Commission> getAllCommissions(){
        return commissionRepository.findAll();
    }

    public Long createCommission(CreateCommissionRequest createCommissionRequest){
        System.out.println("createCommission: "+ createCommissionRequest.vehicle_id());
        Vehicle vehicle = vehicleService.getVehicleEntityById(createCommissionRequest.vehicle_id());
        Client client = clientService.getClientEntityById(createCommissionRequest.client_id());
        Worker worker = workerService.getWorkerEntityById(createCommissionRequest.worker_id());

        Commission commission = CommissionBuilder.getBase()
                .buildVehicle(vehicle)
                .buildClient(client)
                .buildWorker(worker)
                .build();

        Commission savedCommission = commissionRepository.save(commission);

        return savedCommission.getId();
    }

}

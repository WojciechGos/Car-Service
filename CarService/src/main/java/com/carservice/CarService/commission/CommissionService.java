package com.carservice.CarService.commission;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientService;
import com.carservice.CarService.cost.Cost;
import com.carservice.CarService.cost.CostRequest;
import com.carservice.CarService.cost.CostService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleService;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Commission getCommissionById(Long commissionId) {
        return commissionRepository.findById(commissionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Commission with id [%s] not found.".formatted(commissionId)
                ));
    }

    public Long createCommission(CreateCommissionRequest createCommissionRequest){
        Vehicle vehicle = vehicleService.getVehicleEntityById(createCommissionRequest.vehicleId());
        Client client = clientService.getClientEntityById(createCommissionRequest.clientId());
        Worker worker = workerService.getWorkerEntityById(createCommissionRequest.workerId());

        Commission commission = CommissionBuilder.getBase()
                .buildVehicle(vehicle)
                .buildClient(client)
                .buildWorker(worker)
                .build();

        Commission savedCommission = commissionRepository.save(commission);

        return savedCommission.getId();
    }

    public void updateCommission(Long commissionId, UpdateCommissionRequest updateCommissionRequest) {
        Commission updatedCommission = commissionRepository.findById(commissionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Commission with id [%s] not found.".formatted(commissionId)
                ));

        if(updateCommissionRequest.vehicleId() != null) {
            Vehicle vehicle = vehicleService.getVehicleEntityById(updateCommissionRequest.vehicleId());
            updatedCommission.setVehicle(vehicle);
        }

        if(updateCommissionRequest.clientId() != null) {
            Client client = clientService.getClientEntityById(updateCommissionRequest.clientId());
            updatedCommission.setClient(client);
        }

        if(updateCommissionRequest.workerId() != null) {
            Worker worker = workerService.getWorkerEntityById(updateCommissionRequest.workerId());
            updatedCommission.setContractor(worker);
        }

        if(updateCommissionRequest.description() != null) {
            updatedCommission.setDescription(updateCommissionRequest.description());
        }

        if(updateCommissionRequest.commissionStatus() != null) {
            updatedCommission.setCommissionStatus(updateCommissionRequest.commissionStatus());
        }

        commissionRepository.save(updatedCommission);
    }

    public void saveCommission(Commission commission){
        commissionRepository.save(commission);
    }
    public void deleteCommission(Long commissionId) {
        commissionRepository.deleteById(commissionId);
    }

}

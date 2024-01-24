package com.carservice.CarService.methodsTests;
import com.carservice.CarService.commission.*;
import com.carservice.CarService.client.*;
import com.carservice.CarService.vehicles.*;
import com.carservice.CarService.worker.*;
import com.carservice.CarService.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommissionServiceTest {

    @Mock
    private CommissionRepository commissionRepository;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private ClientService clientService;

    @Mock
    private WorkerService workerService;

    @InjectMocks
    private CommissionService commissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCommissions() {
        List<Commission> commissionList = new ArrayList<>();
        when(commissionRepository.findAll()).thenReturn(commissionList);

        List<Commission> result = commissionService.getAllCommissions();

        assertEquals(commissionList, result);
    }

    @Test
    void testGetCommissionById() {
        Long commissionId = 1L;
        Commission commission = new Commission();
        when(commissionRepository.findById(commissionId)).thenReturn(Optional.of(commission));

        Commission result = commissionService.getCommissionById(commissionId);

        assertEquals(commission, result);
    }

    @Test
    void testGetCommissionByIdNotFound() {
        Long commissionId = 1L;
        when(commissionRepository.findById(commissionId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> commissionService.getCommissionById(commissionId));
    }

    @Test
    void testCreateCommission() {

        CreateCommissionRequest request = new CreateCommissionRequest(0L, 0L, 0L, "Commission 1");

        Vehicle vehicle = new Vehicle();
        Client client = new Client();
        Worker worker = new Worker();

        when(vehicleService.getVehicleEntityById(anyLong())).thenReturn(vehicle);
        when(clientService.getClientEntityById(anyLong())).thenReturn(client);
        when(workerService.getWorkerEntityById(anyLong())).thenReturn(worker);

        Commission savedCommission = new Commission();
        when(commissionRepository.save(any(Commission.class))).thenReturn(savedCommission);

        Long result = commissionService.createCommission(request);

        verify(commissionRepository, times(1)).save(any(Commission.class));
    }

    @Test
    void testUpdateCommission() {
        Long commissionId = 1L;
        UpdateCommissionRequest request = new UpdateCommissionRequest(1L, 1L, 1L, "Commission 2",  CommissionStatus.IN_PROGRESS);

        Commission existingCommission = new Commission();
        when(commissionRepository.findById(commissionId)).thenReturn(Optional.of(existingCommission));

        when(vehicleService.getVehicleEntityById(anyLong())).thenReturn(new Vehicle());
        when(clientService.getClientEntityById(anyLong())).thenReturn(new Client());
        when(workerService.getWorkerEntityById(anyLong())).thenReturn(new Worker());

        commissionService.updateCommission(commissionId, request);

        verify(commissionRepository, times(1)).save(any(Commission.class));
    }

    @Test
    void testUpdateCommissionNotFound() {
        Long commissionId = 1L;
        UpdateCommissionRequest request = new UpdateCommissionRequest(2L, 2L, 2L, "Commission 3",  CommissionStatus.IN_PROGRESS);

        when(commissionRepository.findById(commissionId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> commissionService.updateCommission(commissionId, request));
    }

    @Test
    void testDeleteCommission() {
        Long commissionId = 1L;

        commissionService.deleteCommission(commissionId);

        verify(commissionRepository, times(1)).deleteById(commissionId);
    }
}

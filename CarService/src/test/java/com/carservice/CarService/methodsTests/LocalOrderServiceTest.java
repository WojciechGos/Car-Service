package com.carservice.CarService.methodsTests;
import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartService;
import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionService;
import com.carservice.CarService.cost.Cost;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.localOrder.*;
import com.carservice.CarService.order.*;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocalOrderServiceTest {

    @Mock
    private LocalOrderRepository localOrderRepository;

    @Mock
    private WorkerService workerService;

    @Mock
    private SparePartService sparePartService;

    @Mock
    private OrderSparePartService orderSparePartService;

    @Mock
    private CommissionService commissionService;

    @InjectMocks
    private LocalOrderService localOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getAllLocalOrders() {

        LocalOrderRepository localOrderRepository = mock(LocalOrderRepository.class);
        LocalOrderMapper localOrderMapper = mock(LocalOrderMapper.class);

        LocalOrderService localOrderService = new LocalOrderService(localOrderRepository, workerService, sparePartService, orderSparePartService, commissionService, localOrderMapper);

        List<LocalOrder> mockLocalOrders = new ArrayList<>();
        mockLocalOrders.add(new LocalOrder());

        List<LocalOrderDTO> mockLocalOrderDTOs = new ArrayList<>();
        mockLocalOrderDTOs.add(new LocalOrderDTO(1L, LocalDateTime.now(), null, OrderStatus.NEW, new Commission(), new ArrayList<>()));

        when(localOrderRepository.findAll()).thenReturn(mockLocalOrders);
        when(localOrderMapper.map(any())).thenReturn(new LocalOrderDTO(1L, LocalDateTime.now(), null, OrderStatus.NEW, new Commission(), new ArrayList<>()));

        List<LocalOrderDTO> result = localOrderService.getAllLocalOrders();

        assertNotNull(result);
        assertEquals(mockLocalOrders.size(), result.size());

        verify(localOrderRepository, times(1)).findAll();
        verify(localOrderMapper, times(mockLocalOrders.size())).map(any());
    }

    @Test
    void getLocalOrderByWorkerId_existingWorker() {
        // Mock data
        Long workerId = 1L;
        Worker worker = new Worker();

        List<LocalOrder> localOrders = new ArrayList<>();
        LocalOrder localOrder = new LocalOrder(worker, LocalDateTime.now());
        localOrders.add(localOrder);
        when(localOrderRepository.findByWorkerId(workerId)).thenReturn(localOrders);

        // Call the method
        LocalOrder result = localOrderService.getLocalOrderByWorkerId(workerId);

        // Assertions
        assertNotNull(result);
        assertEquals(localOrder, result);
    }

    @Test
    void getLocalOrderByWorkerId_nonExistingWorker() {
        // Mock data
        Long workerId = 1L;
        when(localOrderRepository.findByWorkerId(workerId)).thenReturn(new ArrayList<>());

        // Call the method
        LocalOrder result = localOrderService.getLocalOrderByWorkerId(workerId);

        // Assertions
        assertNull(result);
    }

    void addItemToLocalOrder_sufficientQuantity() {
        // Mock data
        Long sparePartId = 1L;
        Long commissionId = 2L;
        CreateLocalOrderRequest localOrderRequest = new CreateLocalOrderRequest("email@example.com", 5, commissionId);
        Worker worker = new Worker();

        LocalOrder existingLocalOrder = new LocalOrder(worker, LocalDateTime.now());
        existingLocalOrder.setId(3L);
        SparePart sparePart = new SparePart();
        sparePart.setId(sparePartId);

        when(workerService.getWorkerByEmail(anyString())).thenReturn(worker);
        when(localOrderRepository.findByWorkerId(worker.getId())).thenReturn(List.of(existingLocalOrder));
        when(sparePartService.getSparePartById(sparePartId)).thenReturn(sparePart);
        when(orderSparePartService.createOrderSparePart(any(OrderSparePart.class))).thenReturn(new OrderSparePart());

        // Call the method
        Long result = localOrderService.addItemToLocalOrder(sparePartId, localOrderRequest);

        // Assertions
        assertNotNull(result);
        assertEquals(existingLocalOrder.getId(), result);
        verify(localOrderRepository, times(1)).save(any(LocalOrder.class));
    }

    @Test
    void addItemToLocalOrder_insufficientQuantity() {
        // Mock data
        Long sparePartId = 1L;
        CreateLocalOrderRequest localOrderRequest = new CreateLocalOrderRequest("email@example.com", 5, 2L);
        Worker worker = new Worker();

        LocalOrder existingLocalOrder = new LocalOrder(worker, LocalDateTime.now());
        existingLocalOrder.setId(3L);
        SparePart sparePart = new SparePart();
        sparePart.setId(sparePartId);
        sparePart.setQuantity(2);

        when(workerService.getWorkerByEmail(anyString())).thenReturn(worker);
        when(localOrderRepository.findByWorkerId(worker.getId())).thenReturn(List.of(existingLocalOrder));
        when(sparePartService.getSparePartById(sparePartId)).thenReturn(sparePart);

        // Call the method and assert ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> localOrderService.addItemToLocalOrder(sparePartId, localOrderRequest));
    }

    @Test
    void getLocalOrderById_existingOrder() {
        // Mock data
        Long localOrderId = 1L;
        LocalOrder localOrder = new LocalOrder();
        localOrder.setId(localOrderId);
        when(localOrderRepository.findById(localOrderId)).thenReturn(Optional.of(localOrder));

        // Call the method
        LocalOrder result = localOrderService.getLocalOrderById(localOrderId);

        // Assertions
        assertNotNull(result);
        assertEquals(localOrderId, result.getId());
    }

    @Test
    void getLocalOrderById_nonExistingOrder() {
        // Mock data
        Long localOrderId = 1L;
        when(localOrderRepository.findById(localOrderId)).thenReturn(Optional.empty());

        // Call the method and assert ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> localOrderService.getLocalOrderById(localOrderId));
    }

    @Test
    void updateLocalOrder_statusToNew() {
        // Mock data
        Long localOrderId = 1L;
        UpdateLocalOrderRequest localOrderRequest = new UpdateLocalOrderRequest(OrderStatus.NEW);
        LocalOrder existingLocalOrder = new LocalOrder();
        existingLocalOrder.setId(localOrderId);
        existingLocalOrder.setOrderStatus(OrderStatus.CREATING);

        when(localOrderRepository.findById(localOrderId)).thenReturn(Optional.of(existingLocalOrder));

        // Call the method
        localOrderService.updateLocalOrder(localOrderId, localOrderRequest);

        // Assertions
        assertEquals(OrderStatus.NEW, existingLocalOrder.getOrderStatus());
        verify(localOrderRepository, times(1)).save(existingLocalOrder);
    }

}

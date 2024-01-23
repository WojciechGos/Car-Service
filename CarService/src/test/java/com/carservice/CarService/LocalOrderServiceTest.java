package com.carservice.CarService;
import com.carservice.CarService.OrderSparePart.OrderSparePartService;
import com.carservice.CarService.localOrder.*;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    private Warehouse warehouse;

    @Mock
    private LocalOrderMapper localOrderMapper;

    @InjectMocks
    private LocalOrderService localOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLocalOrders() {
        // Given
        LocalOrder localOrder = new LocalOrder(new Worker(), LocalDateTime.now());
        when(localOrderRepository.findAll()).thenReturn(Collections.singletonList(localOrder));

        // When
        List<LocalOrderDTO> result = localOrderService.getAllLocalOrders();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(localOrderMapper, times(1)).map(any(LocalOrder.class));
    }
/*
    @Test
    void testGetLocalOrderByWorkerId() {
        // Given
        Long workerId = 1L;
        Worker worker = new Worker();
        LocalOrder localOrder = new LocalOrder(worker, LocalDateTime.now());
        when(localOrderRepository.findByWorkerId(workerId)).thenReturn(Collections.singletonList(localOrder));

        // When
        LocalOrder result = localOrderService.getLocalOrderByWorkerId(workerId);

        // Then
        assertNotNull(result);
        assertEquals(workerId, result.getWorker().getId());
    }

    @Test
    void testCreateLocalOrder() {
        // Given
        Long workerId = 1L;
        Worker worker = new Worker();
        when(workerService.getWorkerEntityById(workerId)).thenReturn(worker);
        when(localOrderRepository.save(any(LocalOrder.class))).thenReturn(new LocalOrder());

        // When
        LocalOrder result = localOrderService.createLocalOrder(workerId);

        // Then
        assertNotNull(result);
        assertEquals(workerId, result.getWorker().getId());
        assertEquals(OrderStatus.CREATING, result.getOrderStatus());
    }

 */
}

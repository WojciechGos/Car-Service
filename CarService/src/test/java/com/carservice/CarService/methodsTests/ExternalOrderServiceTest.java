package com.carservice.CarService.methodsTests;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.item.Item;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemService;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerService;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.wholesaler.WholesalerAdapterIPARTS;
import com.carservice.CarService.wholesaler.WholesalerAdapterSTARTHURT;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import com.carservice.CarService.externalOrder.*;
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

class ExternalOrderServiceTest {

    @Mock
    private ExternalOrderRepository externalOrderRepository;

    @Mock
    private WorkerService workerService;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private ProducerService producerService;

    @Mock
    private WholesalerAdapterIPARTS wholesalerAdapterIPARTS;

    @Mock
    private WholesalerAdapterSTARTHURT wholesalerAdapterSTARTHURT;

    @Mock
    private SparePartService sparePartService;

    @Mock
    private ExternalOrderMapper externalOrderMapper;

    @InjectMocks
    private ExternalOrderService externalOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void getAllExternalOrders() {
//        ExternalOrderRepository externalOrderRepository = mock(ExternalOrderRepository.class);
//        ExternalOrderMapper externalOrderMapper = mock(ExternalOrderMapper.class);
//
//        ExternalOrderService externalOrderService = new ExternalOrderService(
//            externalOrderRepository,
//            workerService,
//            orderItemService,
//            producerService,
//            wholesalerAdapterIPARTS,
//            wholesalerAdapterSTARTHURT,
//            sparePartService,
//            externalOrderMapper
//        );
//
//        List<ExternalOrder> mockExternalOrders = new ArrayList<>();
//        mockExternalOrders.add(new ExternalOrder());
//
//        List<ExternalOrderDTO> mockExternalOrderDTOs = new ArrayList<>();
//        mockExternalOrderDTOs.add(new ExternalOrderDTO(1L, LocalDateTime.now(), null, OrderStatus.NEW, new ArrayList<>()));
//
//        when(externalOrderRepository.findAll()).thenReturn(mockExternalOrders);
//        when(externalOrderMapper.map(any())).thenReturn(new ExternalOrderDTO(1L, LocalDateTime.now(), null, OrderStatus.NEW, new ArrayList<>()));
//
//        List<ExternalOrderDTO> result = externalOrderService.getAllExternalOrders();
//
//        assertNotNull(result);
//        assertEquals(mockExternalOrders.size(), result.size());
//
//        verify(externalOrderRepository, times(1)).findAll();
//        verify(externalOrderMapper, times(mockExternalOrders.size())).map(any());
//    }

    @Test
    void getExternalOrderByWorkerId_existingWorker() {
        // Mock data
        Long workerId = 1L;
        Worker worker = new Worker();

        List<ExternalOrder> externalOrders = new ArrayList<>();
        ExternalOrder externalOrder = new ExternalOrder(worker, LocalDateTime.now());
        externalOrders.add(externalOrder);
        when(externalOrderRepository.findByWorkerId(workerId)).thenReturn(externalOrders);

        // Call the method
        ExternalOrder result = externalOrderService.getExternalOrderByWorkerId(workerId);

        // Assertions
        assertNotNull(result);
        assertEquals(externalOrder, result);
    }

    @Test
    void getExternalOrderByWorkerId_nonExistingWorker() {
        // Mock data
        Long workerId = 1L;
        when(externalOrderRepository.findByWorkerId(workerId)).thenReturn(new ArrayList<>());

        // Call the method
        ExternalOrder result = externalOrderService.getExternalOrderByWorkerId(workerId);

        // Assertions
        assertNull(result);
    }

//    @Test
//    void addItemToExternalOrder_sufficientQuantity() {
//        // Mock data
//        Long orderItemId = 1L;
//        OrderItemDTO orderItemDTO = new OrderItemDTO(1L, "Tire", new BigDecimal("4.00"), 50, 3L, "TIRE-MAX", "IPART", "Indeks opony TM123,\n" +
//            "Rozmiar 205/55R16, Typ opony - Letnia, Indeks prędkości -H, Index nośności - 91");
//
//        CreateExternalOrderRequest externalOrderRequest = new CreateExternalOrderRequest("email@example.com", 5);
//        Worker worker = new Worker();
//
//        ExternalOrder existingExternalOrder = new ExternalOrder(worker, LocalDateTime.now());
//        existingExternalOrder.setId(3L);
//        OrderItem orderItem = new OrderItem();
//        orderItem.setExternalOrderItemId(orderItemId);
//
//        when(workerService.getWorkerByEmail(anyString())).thenReturn(worker);
//        when(externalOrderRepository.findByWorkerId(worker.getId())).thenReturn(List.of(existingExternalOrder));
//        when(orderItemService.createOrderItem(any(OrderItem.class))).thenReturn(orderItem);
//
//        // Call the method
//        Long result = externalOrderService.addItemToExternalOrder(externalOrderRequest, orderItemDTO);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals(existingExternalOrder.getId(), result);
//        verify(externalOrderRepository, times(1)).save(any(ExternalOrder.class));
//    }

//    @Test
//    void addItemToExternalOrder_insufficientQuantity() {
//        // Mock data
//        Long orderItemId = 1L;
//        OrderItemDTO orderItemDTO = new OrderItemDTO(1L, "Tire", new BigDecimal("4.00"), 50, 3L, "TIRE-MAX", "IPART", "Indeks opony TM123,\n" +
//            "Rozmiar 205/55R16, Typ opony - Letnia, Indeks prędkości -H, Index nośności - 91");
//
//        CreateExternalOrderRequest externalOrderRequest = new CreateExternalOrderRequest("email@example.com", 5);
//        Worker worker = new Worker();
//
//        ExternalOrder existingExternalOrder = new ExternalOrder(worker, LocalDateTime.now());
//        existingExternalOrder.setId(3L);
//        OrderItem orderItem = new OrderItem();
//        orderItem.setExternalOrderItemId(orderItemId);
//        orderItem.setQuantity(2);
//
//        when(workerService.getWorkerByEmail(anyString())).thenReturn(worker);
//        when(externalOrderRepository.findByWorkerId(worker.getId())).thenReturn(List.of(existingExternalOrder));
//        when(orderItemService.createOrderItem(any(OrderItem.class))).thenReturn(orderItem);
//
//        assertDoesNotThrow(() -> externalOrderService.addItemToExternalOrder(externalOrderRequest, orderItemDTO));
//    }

    @Test
    void updateExternalOrder_statusToNew() {
        // Mock data
        Long externalOrderId = 1L;
        UpdateExternalOrder externalOrderRequest = new UpdateExternalOrder(OrderStatus.NEW);
        ExternalOrder existingExternalOrder = new ExternalOrder();
        existingExternalOrder.setId(externalOrderId);
        existingExternalOrder.setOrderStatus(OrderStatus.CREATING);
        when(externalOrderRepository.findById(externalOrderId)).thenReturn(Optional.of(existingExternalOrder));

        // Call the method
        externalOrderService.updateExternalOrder(externalOrderId, externalOrderRequest);

        // Assertions
        assertEquals(OrderStatus.NEW, existingExternalOrder.getOrderStatus());
        verify(externalOrderRepository, times(1)).save(existingExternalOrder);
    }

    @Test
    void updateExternalOrder_statusToInProgress() {
        // Mock data
        Long externalOrderId = 1L;
        UpdateExternalOrder externalOrderRequest = new UpdateExternalOrder(OrderStatus.IN_PROGRESS);
        ExternalOrder existingExternalOrder = new ExternalOrder();
        existingExternalOrder.setId(externalOrderId);
        existingExternalOrder.setOrderStatus(OrderStatus.CREATING);
        when(externalOrderRepository.findById(externalOrderId)).thenReturn(Optional.of(existingExternalOrder));

        // Call the method
        externalOrderService.updateExternalOrder(externalOrderId, externalOrderRequest);

        // Assertions
        assertEquals(OrderStatus.IN_PROGRESS, existingExternalOrder.getOrderStatus());
        verify(externalOrderRepository, times(1)).save(existingExternalOrder);
    }

    @Test
    void updateExternalOrder_statusToCompleted() {
        // Mock data
        Long externalOrderId = 1L;
        UpdateExternalOrder externalOrderRequest = new UpdateExternalOrder(OrderStatus.COMPLETED);
        ExternalOrder existingExternalOrder = new ExternalOrder();
        existingExternalOrder.setId(externalOrderId);
        existingExternalOrder.setOrderStatus(OrderStatus.IN_PROGRESS);
        List<OrderItem> orderItems = new ArrayList<>();
        existingExternalOrder.setItems(orderItems);
        when(externalOrderRepository.findById(externalOrderId)).thenReturn(Optional.of(existingExternalOrder));

        // Call the method
        externalOrderService.updateExternalOrder(externalOrderId, externalOrderRequest);

        // Assertions
        assertEquals(OrderStatus.COMPLETED, existingExternalOrder.getOrderStatus());
        assertNotNull(existingExternalOrder.getReceiveDate());
        verify(externalOrderRepository, times(1)).save(existingExternalOrder);
    }

    @Test
    void updateExternalOrder_statusToCancelled() {
        // Mock data
        Long externalOrderId = 1L;
        UpdateExternalOrder externalOrderRequest = new UpdateExternalOrder(OrderStatus.CANCELLED);
        ExternalOrder existingExternalOrder = new ExternalOrder();
        existingExternalOrder.setId(externalOrderId);
        existingExternalOrder.setOrderStatus(OrderStatus.CREATING);
        when(externalOrderRepository.findById(externalOrderId)).thenReturn(Optional.of(existingExternalOrder));

        // Call the method
        externalOrderService.updateExternalOrder(externalOrderId, externalOrderRequest);

        // Assertions
        assertEquals(OrderStatus.CANCELLED, existingExternalOrder.getOrderStatus());
        assertNotNull(existingExternalOrder.getReceiveDate());
        verify(externalOrderRepository, times(1)).save(existingExternalOrder);
    }

//    @Test
//    void getExternalOrderByWorkerEmail_orderInCreatingStatus() {
//        // Mock data
//        String workerEmail = "karol@gmail.com";
//        Worker worker = new Worker();
//        worker.setEmail(workerEmail);
//
//        ExternalOrder existingExternalOrder = new ExternalOrder();
//        existingExternalOrder.setOrderStatus(OrderStatus.CREATING);
//
//        when(workerService.getWorkerByEmail(workerEmail)).thenReturn(worker);
//        when(externalOrderRepository.findByWorkerId(worker.getId())).thenReturn(List.of(existingExternalOrder));
//        when(externalOrderMapper.map(existingExternalOrder)).thenReturn(new ExternalOrderDTO(1L,   LocalDateTime.now(),null, OrderStatus.CANCELLED, new ArrayList<>()));
//
//        // Call the method
//        ExternalOrderDTO result = externalOrderService.getExternalOrderByWorkerEmail(workerEmail);
//
//        // Assertions
//        assertNotNull(result);
//        verify(externalOrderMapper, times(1)).map(existingExternalOrder);
//    }

    @Test
    void getExternalOrderByWorkerEmail_orderNotInCreatingStatus() {
        // Mock data
        String workerEmail = "worker@example.com";
        Worker worker = new Worker();
        worker.setEmail(workerEmail);

        ExternalOrder existingExternalOrder = new ExternalOrder();
        existingExternalOrder.setOrderStatus(OrderStatus.COMPLETED);

        when(workerService.getWorkerByEmail(workerEmail)).thenReturn(worker);
        when(externalOrderRepository.findByWorkerId(worker.getId())).thenReturn(List.of(existingExternalOrder));

        // Call the method
        ExternalOrderDTO result = externalOrderService.getExternalOrderByWorkerEmail(workerEmail);

        // Assertions
        assertNull(result);
    }

}

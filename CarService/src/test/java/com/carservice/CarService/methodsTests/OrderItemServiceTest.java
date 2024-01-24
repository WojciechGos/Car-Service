package com.carservice.CarService.methodsTests;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.orderItem.*;
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

class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getOrderItemById_existingId() {
        // Mock data
        Long orderItemId = 1L;
        OrderItem orderItem = new OrderItem();
        when(orderItemRepository.findById(orderItemId)).thenReturn(Optional.of(orderItem));

        // Call the method
        OrderItem result = orderItemService.getOrderItemById(orderItemId);

        // Assertions
        assertNotNull(result);
        assertEquals(orderItem, result);
    }

    @Test
    void getOrderItemById_nonExistingId() {
        // Mock data
        Long orderItemId = 1L;
        when(orderItemRepository.findById(orderItemId)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> orderItemService.getOrderItemById(orderItemId));
    }

    @Test
    void getAllOrderItems() {
        // Mock data
        List<OrderItem> orderItems = new ArrayList<>();
        when(orderItemRepository.findAll()).thenReturn(orderItems);

        // Call the method
        List<OrderItem> result = orderItemService.getAllOrderItems();

        // Assertions
        assertEquals(orderItems, result);
    }

    @Test
    void createOrderItem() {
        OrderItemRepository orderItemRepository = mock(OrderItemRepository.class);

        OrderItemService orderItemService = new OrderItemService(orderItemRepository);
        OrderItem orderItemToSave = new OrderItem();

        when(orderItemRepository.save(any())).thenAnswer(invocation -> {
            OrderItem savedOrderItem = invocation.getArgument(0);
            savedOrderItem.setId(1L);
            return savedOrderItem;
        });

        OrderItem result = orderItemService.createOrderItem(orderItemToSave);

        verify(orderItemRepository, times(1)).save(any());
        assertNotNull(result);

        assertNotNull(result.getId());
        assertEquals(1L, result.getId());
    }



}

package com.carservice.CarService.methodsTests;
import com.carservice.CarService.OrderSparePart.*;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.sparePart.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderSparePartServiceTest {

    @Mock
    private OrderSparePartRepository orderSparePartRepository;

    @InjectMocks
    private OrderSparePartService orderSparePartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createOrderSparePartWithObject() {
        // Mock data
        OrderSparePart orderSparePart = new OrderSparePart();
        when(orderSparePartRepository.save(orderSparePart)).thenReturn(orderSparePart);

        // Call the method
        OrderSparePart result = orderSparePartService.createOrderSparePart(orderSparePart);

        // Assertions
        verify(orderSparePartRepository, times(1)).save(orderSparePart);
        assertNotNull(result);
        assertEquals(orderSparePart, result);
    }

    @Test
    void createOrderSparePartWithDetails() {
        // Mock data
        SparePart sparePart = new SparePart();
        Integer quantity = 2;
        OrderSparePart orderSparePart = new OrderSparePart(sparePart, quantity);

        // when
        when(orderSparePartRepository.save(any(OrderSparePart.class))).thenReturn(orderSparePart);

        // Call the method
        OrderSparePart result = orderSparePartService.createOrderSparePart(sparePart, quantity);

        // Assertions
        verify(orderSparePartRepository, times(1)).save(any(OrderSparePart.class));
        assertNotNull(result);
        assertEquals(orderSparePart, result);
    }



    @Test
    void getOrderSparePartById_existingId() {
        // Mock data
        Long orderSparePartId = 1L;
        OrderSparePart orderSparePart = new OrderSparePart();
        when(orderSparePartRepository.findById(orderSparePartId)).thenReturn(Optional.of(orderSparePart));

        // Call the method
        OrderSparePart result = orderSparePartService.getOrderSparePartById(orderSparePartId);

        // Assertions
        assertNotNull(result);
        assertEquals(orderSparePart, result);
    }

    @Test
    void getOrderSparePartById_nonExistingId() {
        // Mock data
        Long orderSparePartId = 1L;
        when(orderSparePartRepository.findById(orderSparePartId)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> orderSparePartService.getOrderSparePartById(orderSparePartId));
    }
}

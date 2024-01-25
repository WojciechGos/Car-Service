package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.wholesaler.*;
import com.carservice.CarService.orderItem.*;
import com.carservice.CarService.requestItem.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WholesalerAdapterIPARTSTest {

    @Mock
    private OrderItemMapper orderItemMapper;

    @Mock
    private WholesalerIPARTS wholesalerIPARTS;

    @InjectMocks
    private WholesalerAdapterIPARTS wholesalerAdapterIPARTS;

//    @Test
//    public void orderItem() {
//        // Arrange
//        Long itemId = 1L;
//        RequestItemDTO requestItemDTO = new RequestItemDTO(1L, "TIRE-MAX", "IPART", "Description", "Tire", new BigDecimal("4.00"), 10);
//        OrderItemDTO expectedOrderItemDTO = new OrderItemDTO(
//            2L, "Tire", new BigDecimal("4.00"), 50, 3L, "TIRE-MAX", "IPART", "Description");
//
//        when(wholesalerIPARTS.post(itemId)).thenReturn(requestItemDTO);
//        when(orderItemMapper.map(requestItemDTO)).thenReturn(expectedOrderItemDTO);
//
//        // Act
//        OrderItemDTO result = wholesalerAdapterIPARTS.orderItem(itemId);
//
//        // Assert
//        assertEquals(expectedOrderItemDTO, result);
//
//        verify(wholesalerIPARTS, times(1)).post(itemId);
//        verify(orderItemMapper, times(1)).map(requestItemDTO);
//    }

//    @Test
//    public void getOrderItem() {
//        // Arrange
//        List<RequestItemDTO> requestItemList = Arrays.asList(
//            new RequestItemDTO(1L, "TIRE1", "IPART", "Description1", "Tire", new BigDecimal("5.00"), 20),
//            new RequestItemDTO(2L, "TIRE2", "IPART", "Description2", "Tire", new BigDecimal("6.00"), 15)
//        );
//        List<OrderItemDTO> expectedOrderItemList = Arrays.asList(
//            new OrderItemDTO(1L, "Tire", new BigDecimal("5.00"), 20, 4L, "TIRE1", "IPART", "Description1"),
//            new OrderItemDTO(2L, "Tire", new BigDecimal("6.00"), 15, 6L, "TIRE2", "IPART", "Description2")
//        );
//
//        when(wholesalerIPARTS.get()).thenReturn(requestItemList);
//        when(orderItemMapper.map(any(RequestItemDTO.class))).thenReturn(expectedOrderItemList.get(0), expectedOrderItemList.get(1));
//
//        // Act
//        List<OrderItemDTO> result = wholesalerAdapterIPARTS.getOrderItemList();
//
//        // Assert
//        assertEquals(expectedOrderItemList, result);
//
//        verify(wholesalerIPARTS, times(1)).get();
//        verify(orderItemMapper, times(2)).map(any(RequestItemDTO.class));
//    }
}

package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.wholesaler.*;
import com.carservice.CarService.orderItem.*;
import com.carservice.CarService.requestItem.RequestItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WholesalerAdapterSTARTHURTTest {

    private WholesalerAdapterSTARTHURT wholesalerAdapterSTARTHURT;
    private OrderItemMapper orderItemMapper;

    @BeforeEach
    void setUp() {
        orderItemMapper = mock(OrderItemMapper.class);
        wholesalerAdapterSTARTHURT = new WholesalerAdapterSTARTHURT(orderItemMapper);
    }

//    @Test
//    void orderItem_shouldMapOrderItemDTO() {
//        // Arrange
//        Long itemId = 1L;
//        RequestItemDTO requestItemDTO = new RequestItemDTO(1L, "PART123", "STARTHURT", "Description", "Part", new BigDecimal("10.00"), 5);
//        OrderItemDTO expectedOrderItemDTO = new OrderItemDTO(
//            null,
//            "Part", new BigDecimal("10.00"), 50, 3L, "PART123", "STARTHURT", "Description");
//
//        WholesalerAdapterSTARTHURT wholesalerAdapterSTARTHURT = mock(WholesalerAdapterSTARTHURT.class);
//
//        when(wholesalerAdapterSTARTHURT.post(itemId)).thenReturn(requestItemDTO);
//        when(orderItemMapper.map(requestItemDTO)).thenReturn(expectedOrderItemDTO);
//
//        // Act
//        OrderItemDTO result = wholesalerAdapterSTARTHURT.orderItem(itemId);
//
//        // Assert
//        assertEquals(expectedOrderItemDTO, result);
//
//        verify(wholesalerAdapterSTARTHURT, times(1)).post(itemId);
//        verify(orderItemMapper, times(1)).map(requestItemDTO);
//    }

//    @Test
//    void getOrderItemList_shouldMapOrderItemDTOList() {
//        // Arrange
//        List<RequestItemDTO> requestItemDTOList = Collections.singletonList(new RequestItemDTO(1L, "PART456", "STARTHURT", "Description", "Part", new BigDecimal("15.00"), 8));
//        List<OrderItemDTO> expectedOrderItemDTOList = Collections.singletonList(new OrderItemDTO(
//            null, "Part", new BigDecimal("15.00"), 40, 2L, "PART456", "STARTHURT", "Description"));
//
//        WholesalerAdapterSTARTHURT wholesalerAdapterSTARTHURT = mock(WholesalerAdapterSTARTHURT.class);
//
//        when(wholesalerAdapterSTARTHURT.get()).thenReturn(requestItemDTOList);
//        when(orderItemMapper.map(any(RequestItemDTO.class))).thenReturn(expectedOrderItemDTOList.get(0));
//
//        // Act
//        List<OrderItemDTO> result = wholesalerAdapterSTARTHURT.getOrderItemList();
//
//        // Assert
//        assertEquals(expectedOrderItemDTOList, result);
//        verify(wholesalerAdapterSTARTHURT, times(1)).get();
//        verify(orderItemMapper, times(1)).map(any(RequestItemDTO.class));
//    }
}

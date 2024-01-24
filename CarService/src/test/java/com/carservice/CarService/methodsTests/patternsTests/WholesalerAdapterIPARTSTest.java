package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.wholesaler.*;
import com.carservice.CarService.orderItem.*;
import com.carservice.CarService.requestItem.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WholesalerAdapterIPARTSTest {

    @Mock
    private WholesalerIPARTS wholesalerIPARTS;

    @Mock
    private OrderItemMapper orderItemMapper;

    @InjectMocks
    private WholesalerAdapterIPARTS wholesalerAdapterIPARTS;

    @Test
    public void OrderItem() {

        Long id = 1L;
        RequestItemDTO requestItemDTO = new RequestItemDTO(1L, "TIRE-MAX", "IPART", "Indeks opony TM123,\n" +
            "Rozmiar 205/55R16, Typ opony - Letnia, Indeks prędkości -H, Index nośności - 91", "Tire",new BigDecimal("4.00"), 10);

        OrderItemDTO orderItemDTO = new OrderItemDTO(   2L,
            "Tire", new BigDecimal("4.00"), 50, 3L, "TIRE-MAX", "IPART", "Indeks opony TM123,\n" +
            "Rozmiar 205/55R16, Typ opony - Letnia, Indeks prędkości -H, Index nośności - 91");

        when(wholesalerIPARTS.post(id)).thenReturn(requestItemDTO);
        when(orderItemMapper.map(requestItemDTO)).thenReturn(orderItemDTO);

        OrderItemDTO result = wholesalerAdapterIPARTS.orderItem(id);

        assertEquals(orderItemDTO, result);
    }

    @Test
    public void getOrderItem() {
        List<RequestItemDTO> response = List.of(new RequestItemDTO(1L, "TIRE-MAX", "IPART", "Indeks opony TM123,\n" +
            "Rozmiar 205/55R16, Typ opony - Letnia, Indeks prędkości -H, Index nośności - 91", "Tire",new BigDecimal("4.00"), 10));
        when(wholesalerIPARTS.get()).thenReturn(response);

        OrderItemDTO orderItemDTO = new OrderItemDTO(   null, // Możesz ustawić null, jeśli id jest generowane automatycznie
            "Tire", new BigDecimal("4.00"), 50, 3L, "TIRE-MAX", "IPART", "Indeks opony TM123,\n" +
            "Rozmiar 205/55R16, Typ opony - Letnia, Indeks prędkości -H, Index nośności - 91");
        when(orderItemMapper.map(response.get(0))).thenReturn(orderItemDTO);

        List<OrderItemDTO> result = wholesalerAdapterIPARTS.getOrderItemList();

        assertEquals(List.of(orderItemDTO), result);
    }
}

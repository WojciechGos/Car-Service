package com.carservice.CarService.OrderSparePart;

import org.springframework.stereotype.Service;

@Service
public class OrderSparePartMapper {
    public OrderSparePartDTO map(final OrderSparePart orderSparePart){



        return new OrderSparePartDTO(
                orderSparePart.getId(),
                orderSparePart.getSparePart().getName(),
                orderSparePart.getSparePart().getId(),
                orderSparePart.getSparePart().getPrice(),
                orderSparePart.getQuantity(),
                1L,
                "VALEO"
        );
    }
}

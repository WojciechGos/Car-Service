package com.carservice.CarService.cost;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartDTO;
import com.carservice.CarService.OrderSparePart.OrderSparePartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CostMapper {
    private final OrderSparePartMapper orderSparePartMapper;
    public CostDTO map(final Cost cost){
        List<OrderSparePart> orderSpareParts = cost.getSpareParts();

        List<OrderSparePartDTO> orderSparePartDTOS = new ArrayList<>();
        for(OrderSparePart orderSparePart : orderSpareParts) {
            OrderSparePartDTO orderSparePartDTO = orderSparePartMapper.map(orderSparePart);
            orderSparePartDTOS.add(orderSparePartDTO);
        }

        return new CostDTO(
                cost.getId(),
                cost.getName(),
                cost.getCreateDate(),
                orderSparePartDTOS,
                cost.getLaborPrice(),
                cost.getTotalCost()
        );
    }
}

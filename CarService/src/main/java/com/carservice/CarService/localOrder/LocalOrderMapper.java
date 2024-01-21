package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePartDTO;
import com.carservice.CarService.OrderSparePart.OrderSparePartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalOrderMapper {
    private final OrderSparePartMapper orderSparePartMapper;

    public LocalOrderDTO map(final LocalOrder localOrder){

        List<OrderSparePartDTO> orderSparePartDTOList = localOrder.getItems().stream().map(orderSparePartMapper::map).collect(Collectors.toList());


        return new LocalOrderDTO(
                localOrder.getId(),
                localOrder.getCreateDate(),
                localOrder.getReceiveDate(),
                localOrder.getOrderStatus(),
                localOrder.getCommission(),
                orderSparePartDTOList
        );
    }
}

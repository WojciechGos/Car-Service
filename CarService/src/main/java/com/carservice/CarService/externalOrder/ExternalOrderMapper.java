package com.carservice.CarService.externalOrder;

import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExternalOrderMapper {
    private final OrderItemMapper orderItemMapper;

    public ExternalOrderDTO map(final ExternalOrder externalOrder) {

        List<OrderItemDTO> orderItemDTOList = externalOrder.getItems().stream().map(orderItemMapper::map).collect(Collectors.toList());

        return new ExternalOrderDTO(
                externalOrder.getId(),
                externalOrder.getCreateDate(),
                externalOrder.getReceiveDate(),
                externalOrder.getOrderStatus(),
                orderItemDTOList,
                externalOrder.getWorker()
        );
    }
}
package com.carservice.CarService.wholesaler;

import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemMapper;
import com.carservice.CarService.requestItem.RequestItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WholesalerAdapterIPARTS extends WholesalerIPARTS implements  WholesalerAdapter {
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDTO orderItem(Long id) {
        RequestItemDTO requestItemDTO = post(id);

        return orderItemMapper.map(requestItemDTO);
    }

    @Override
    public List<OrderItemDTO> getOrderItemList() {
        List<RequestItemDTO> response = get();
        return response.stream().map(orderItemMapper::map).toList();
    }
}

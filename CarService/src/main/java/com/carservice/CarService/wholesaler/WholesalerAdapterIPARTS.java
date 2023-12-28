package com.carservice.CarService.wholesaler;

import com.carservice.CarService.externalOrder.ExternalOrderService;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemMapper;
import com.carservice.CarService.requestItem.RequestItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WholesalerAdapterIPARTS extends WholesalerIPARTS implements  WholesalerAdapter{
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDTO orderItem(Long id) {
        RequestItemDTO requestItemDTO = post(id);
        if(requestItemDTO == null)
            return null; // TODO throw NotFoundException

        OrderItemDTO orderItemDTO = orderItemMapper.map(requestItemDTO);
        return orderItemDTO;
    }

    @Override
    public List<OrderItemDTO> getOrderItemList() {
        List<RequestItemDTO> response = get();
        System.out.println(response);
        List<OrderItemDTO> orderItemDTOList = response.stream().map(orderItemMapper::map).toList();
        return orderItemDTOList;
    }
}

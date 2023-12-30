package com.carservice.CarService.orderItem;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;


    public OrderItem getOrderItemById(Long id){
//        OrderItem orderItem = orderItemRepository.findById(id);
        return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "OrderItem with id [%s] not found.".formatted(id)
        ));
    }

    public List<OrderItem> getAllOrderItems(){
        List<OrderItem> orderItemList = orderItemRepository.findAll();

        return orderItemList;
    }

    public OrderItem createOrderItem(OrderItem orderItem){
        OrderItem saved = orderItemRepository.save(orderItem);
        return saved;
    }

    public Long updateOrderItem(){

        return 0L;
    }

    public void deleteOrderItem(){

    }

}

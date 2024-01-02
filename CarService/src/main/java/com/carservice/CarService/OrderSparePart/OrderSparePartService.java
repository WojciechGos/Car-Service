package com.carservice.CarService.OrderSparePart;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.sparePart.SparePart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderSparePartService {
    private final OrderSparePartRepository orderSparePartRepository;


    public OrderSparePart createOrderSparePart (OrderSparePart orderSparePart){
        OrderSparePart saved = orderSparePartRepository.save(orderSparePart);
        return saved;
    }
    public OrderSparePart createOrderSparePart (SparePart sparePart, Integer quantity){
        OrderSparePart saved = orderSparePartRepository.save(new OrderSparePart(sparePart, quantity));
        return saved;
    }

    public OrderSparePart getOrderSparePartById(Long orderSparePartId){
        return orderSparePartRepository.findById(orderSparePartId).orElseThrow(()->
                new ResourceNotFoundException("OrderSparePart with id [%s] not found".formatted(orderSparePartId))
        );
    }

}

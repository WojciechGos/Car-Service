package com.carservice.CarService.orderItem;

import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerRepository;
import com.carservice.CarService.producer.ProducerService;
import com.carservice.CarService.requestItem.RequestItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderItemMapper {

    private final ProducerService producerService;

    public OrderItemDTO map(final OrderItem orderItem){


        Long producerId = null;
        String producerName = null;

        if(orderItem.getProducer() != null){
            producerId = orderItem.getProducer().getId();
            producerName = orderItem.getProducer().getName();
        }

        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getName(),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                producerId,
                producerName,
                orderItem.getWholesaler()
        );
    }

    public OrderItemDTO map(final RequestItemDTO requestItemDTO){

        Optional<Producer> producer = producerService.getProducerByName(requestItemDTO.producerName());

        Long producerId;
        String producerName;
        if(producer.isPresent()){
            producerId = producer.get().getId();
            producerName = producer.get().getName();
        }else {
            producerId = null;
            producerName = null;
        }

        return new OrderItemDTO(
                requestItemDTO.id(),
                requestItemDTO.itemName(),
                requestItemDTO.price(),
                requestItemDTO.quantity(),
                producerId,
                producerName,
                requestItemDTO.wholesaler()
        );
    }
}
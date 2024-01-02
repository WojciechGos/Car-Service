package com.carservice.CarService.requestItem;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RequestItemMapper {
    public RequestItemDTO map(final RequestItem requestItem){
        return new RequestItemDTO(
                requestItem.getId(),
                requestItem.getProducerName(),
                requestItem.getWholesaler(),
                requestItem.getParameters(),
                requestItem.getName(),
                requestItem.getPrice(),
                requestItem.getQuantity()
        );
    }
}

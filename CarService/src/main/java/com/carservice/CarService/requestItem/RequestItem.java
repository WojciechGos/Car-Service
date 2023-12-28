package com.carservice.CarService.requestItem;

import com.carservice.CarService.item.Item;
import com.carservice.CarService.producer.Producer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestItem extends Item {

    private Long id;
    private String producerName;
    private String wholesaler;
    private String parameters;

    public RequestItem(String name, BigDecimal price, Integer quantity, Long id, String producerName, String wholesaler, String parameters) {
        super(name, price, quantity);
        this.id = id;
        this.producerName = producerName;
        this.wholesaler = wholesaler;
        this.parameters = parameters;
    }
}

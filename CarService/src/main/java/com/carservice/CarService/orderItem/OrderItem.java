package com.carservice.CarService.orderItem;

import com.carservice.CarService.item.Item;
import com.carservice.CarService.producer.Producer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem extends Item {
    @SequenceGenerator(
            name = "order_item_sequence",
            sequenceName = "order_item_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_item_sequence"
    )
    private Long id;
    private String wholesaler;

    public OrderItem(String name, BigDecimal price, Integer quantity, Producer producer, String wholesaler) {
        super(name, price, quantity, producer);
        this.wholesaler = wholesaler;
    }
}

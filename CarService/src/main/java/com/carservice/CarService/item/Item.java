package com.carservice.CarService.item;

import com.carservice.CarService.producer.Producer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Item {
    @NotBlank
    private String name;
    private BigDecimal price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    public Item(String name, BigDecimal price, Integer quantity, Producer producer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
    }

    public Item(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

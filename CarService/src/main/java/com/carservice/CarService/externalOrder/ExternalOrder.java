package com.carservice.CarService.externalOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.order.Order;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
    Class ExternalOrder is used for place orders from external wholesales.

 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ExternalOrder extends Order {
    @SequenceGenerator(
            name = "external_order_sequence",
            sequenceName = "external_order_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "external_order_sequence"
    )
    private Long id;

    @Transient
    private BigDecimal totalCost;

    @OneToMany
    private List<OrderItem> items = new ArrayList<>();

    public ExternalOrder(Worker worker, LocalDateTime createDate) {
        super(worker, createDate);
    }
}

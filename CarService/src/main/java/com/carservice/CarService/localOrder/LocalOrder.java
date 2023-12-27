package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.item.Item;
import com.carservice.CarService.order.Order;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    LocalOrder class purpose is used to place local orders in an auto repair shop.
    It is created by the contractor to obtain spare parts from the warehouse.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class LocalOrder extends Order {
    @SequenceGenerator(
            name = "local_order_sequence",
            sequenceName = "local_order_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "local_order_sequence"
    )
    private Long id;
    // idk co to znaczy
    @OneToMany
    @JoinColumn(name = "commission_id", nullable = false)
    private List<Commission> commissionList;

    @OneToMany
    private List<OrderSparePart> items = new ArrayList<>();


    public LocalOrder(Worker worker, LocalDateTime createDate) {
        super(worker, createDate);
    }


}

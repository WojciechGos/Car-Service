package com.carservice.CarService.order;

import com.carservice.CarService.item.Item;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.worker.Worker;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Order {
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;
    private LocalDateTime createDate;
    private LocalDateTime receiveDate;

//    private List<Item> item;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Transient
    private Warehouse warehouse;

    public Order(Worker worker, LocalDateTime createDate) {
        this.worker = worker;
        this.createDate = createDate;
        this.orderStatus = OrderStatus.NEW;
        this.warehouse = Warehouse.getInstance();
    }
    public void submitOrder(){
        warehouse.processOrder(this);
    }
}

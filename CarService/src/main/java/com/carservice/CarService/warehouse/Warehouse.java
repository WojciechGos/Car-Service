package com.carservice.CarService.warehouse;

import com.carservice.CarService.localOrder.LocalOrder;
import com.carservice.CarService.order.Order;
import com.carservice.CarService.sparePart.SparePart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
    It is mediator class
 */
//@MappedSuperclass
public class Warehouse {
    private static Warehouse instance = null;
//    @ManyToOne
//    @JoinColumn(name = "commission_id", nullable = false)
//    private List<Order> orders;
    private Warehouse(){}

    public void processOrder(Order order){
        System.out.println(order);
        if(order instanceof LocalOrder){
            Long id = ((LocalOrder) order).getId();
            System.out.println(order + " " + id);
        }

        System.out.println(order);
        System.out.println("Processing order:" + order.getWorker());
    }
    // java singleton synchronized ( zabezpiecz przed utworzeniem dwóch instancji warehous)
    public static Warehouse getInstance(){
        if(instance == null)
            instance = new Warehouse();
        return instance;
    }
}

package com.carservice.CarService.warehouse;
import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.localOrder.LocalOrder;
import com.carservice.CarService.order.Order;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
    It is mediator class between LocalOrder and ExternalOrder
 */



@Component
public class Warehouse {

    private final SparePartService sparePartService;
    private static Warehouse instance = null;


    private Warehouse(SparePartService sparePartService){
        this.sparePartService = sparePartService;
    }

    public synchronized void takeSparePart(SparePart sparePart, Integer quantity){

    }

    public synchronized void releaseSparePart(OrderSparePart orderSparePart){

    }


    public synchronized void processOrder(Order order){

        if(order instanceof LocalOrder){
            Long id = ((LocalOrder) order).getId();
            System.out.println(order + " " + id);
        }

        System.out.println("Processing order:" + order.getWorker());
    }
    // java singleton synchronized ( zabezpiecz przed utworzeniem dwóch instancji warehous)
//    public synchronized static Warehouse getInstance(SparePartService sparePartService){
//        if(instance == null)
//            instance = new Warehouse(sparePartService);
//        return instance;
//    }
}

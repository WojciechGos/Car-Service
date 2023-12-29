package com.carservice.CarService.warehouse;
import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.localOrder.LocalOrder;
import com.carservice.CarService.order.Order;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
    It is mediator class between LocalOrder and ExternalOrder
 */



public class Warehouse {

    private final SparePartService sparePartService;
    private static Warehouse instance = null;

    private Warehouse(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }



    public synchronized void takeSparePart(SparePart sparePart, Integer orderedQuantity){
        System.out.println("takeSparePart //////////////////////////////////////////////////////////////////////////");
        sparePart.setQuantity(sparePart.getQuantity() - orderedQuantity);
        sparePartService.updateSparePart(sparePart);
    }

    public synchronized void releaseSparePart(OrderSparePart orderSparePart){

    }

    public synchronized void releaseSparePartListFromOrder(LocalOrder localOrder){
        System.out.println("releaseSparePartListFromOrder");
        Integer quantity;
        for(OrderSparePart orderSparePart: localOrder.getItems()){
            quantity = orderSparePart.getQuantity() + orderSparePart.getSparePart().getQuantity();
            orderSparePart.getSparePart().setQuantity(quantity);
            sparePartService.updateSparePart(orderSparePart.getSparePart());
        }
    }

    public synchronized void addSparePart(SparePart sparePart){

    }

    public synchronized void processOrder(Order order){

        if(order instanceof LocalOrder){
            Long id = ((LocalOrder) order).getId();
            System.out.println(order + " " + id);
        }

        System.out.println("Processing order:" + order.getWorker());
    }
//     java singleton synchronized ( zabezpiecz przed utworzeniem dwóch instancji warehous)
    public synchronized static Warehouse getInstance(SparePartService sparePartService){
        if(instance == null)
            instance = new Warehouse(sparePartService);
        return instance;
    }
}

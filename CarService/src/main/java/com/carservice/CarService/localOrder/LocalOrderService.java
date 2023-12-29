package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.item.Item;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalOrderService {
    private final LocalOrderRepository localOrderRepository;
    private final WorkerService workerService;
    private final SparePartService sparePartService;
    private final OrderSparePartService orderSparePartService;
    private Warehouse warehouse;

    private final LocalOrderMapper localOrderMapper;
    public List<LocalOrderDTO> getAllLocalOrders(){

        List<LocalOrder> localOrder = localOrderRepository.findAll();

        return localOrder.stream().map(localOrderMapper::map).collect(Collectors.toList());
    }

    private LocalOrder createLocalOrder(Long workerId){
        Worker worker = workerService.getWorkerEntityById(workerId);
        LocalOrder localOrder = new LocalOrder(worker, LocalDateTime.now());

        LocalOrder saved = localOrderRepository.save(localOrder);
//        localOrder.submitOrder();
        return saved;
    }



    public Long addItemToLocalOrder(Long sparePartId,CreateLocalOrderRequest localOrderRequest){
        LocalOrder localOrder;
        if(localOrderRequest.localOrderId() == null){
            localOrder = createLocalOrder(localOrderRequest.workerId());
        }else{
            final Long finalLocalOrderId = localOrderRequest.localOrderId();
            localOrder = localOrderRepository.findById(finalLocalOrderId)
                    .orElseThrow(()-> new ResourceNotFoundException("Local order with id [%] not found".formatted(finalLocalOrderId)));
        }

        //
//        localOrder.getWarehouse().

        List<OrderSparePart> tmpList = localOrder.getItems();

        SparePart sparePart = sparePartService.getSparePartById(sparePartId);
        OrderSparePart orderSparePart = orderSparePartService.createOrderSparePart(sparePart, localOrderRequest.quantity());
        tmpList.add(orderSparePart);

        localOrder.setItems(tmpList);
        localOrderRepository.save(localOrder);

        return localOrder.getId();
    }

    public void updateLocalOrder(Long localOrderId, UpdateLocalOrderRequest localOrderRequest){
        LocalOrder updateLocalOrder = localOrderRepository.findById(localOrderId)
                .orElseThrow(()-> new ResourceNotFoundException("LocalOrder with id [%s] not found.".formatted(localOrderId)));

        int currentState = updateLocalOrder.getOrderStatus().getValue();

        if(localOrderRequest.orderStatus() == null)
            return;

        if(localOrderRequest.orderStatus() == OrderStatus.NEW && currentState < OrderStatus.NEW.getValue()){

            updateLocalOrder.setOrderStatus(OrderStatus.NEW);

        }else  if(localOrderRequest.orderStatus() == OrderStatus.IN_PROGRESS && currentState < OrderStatus.IN_PROGRESS.getValue()){

            updateLocalOrder.setOrderStatus(OrderStatus.IN_PROGRESS);

        } else if(localOrderRequest.orderStatus() == OrderStatus.COMPLETED && currentState < OrderStatus.COMPLETED.getValue()){

            updateLocalOrder.setOrderStatus(OrderStatus.COMPLETED);
            updateLocalOrder.setReceiveDate(LocalDateTime.now());

        }else if(localOrderRequest.orderStatus() == OrderStatus.CANCELLED && (currentState == OrderStatus.NEW.getValue() || currentState == OrderStatus.IN_PROGRESS.getValue())){

            updateLocalOrder.setReceiveDate(LocalDateTime.now());
            updateLocalOrder.setOrderStatus(OrderStatus.CANCELLED);
            // release ordered items by calling some warehouse method

        }

        localOrderRepository.save(updateLocalOrder);
    }

}

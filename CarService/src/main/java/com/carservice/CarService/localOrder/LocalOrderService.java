package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.externalOrder.ExternalOrder;
import com.carservice.CarService.item.Item;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerService;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

        return saved;
    }

    public LocalOrder getLocalOrderByWorkerId(Long workerId) {
        List<LocalOrder> localOrder = localOrderRepository.findByWorkerId(workerId);
        if(localOrder.isEmpty())
            return null;
        return localOrder.get(0);
    }

    public Long addItemToLocalOrder(Long sparePartId,CreateLocalOrderRequest localOrderRequest){

        final Worker worker = workerService.getWorkerByEmail(localOrderRequest.email());
        LocalOrder workerLocalOrder = getLocalOrderByWorkerId(worker.getId());

        SparePart sparePart = sparePartService.getSparePartById(sparePartId);



        if((sparePart.getQuantity() - localOrderRequest.quantity()) < 0) {
            throw new ResourceNotFoundException("SparePart with id [%s] not available".formatted(sparePartId));
        }

        warehouse = Warehouse.getInstance(sparePartService);
        warehouse.takeSparePart(sparePart, localOrderRequest.quantity());

        LocalOrder localOrder = getOrCreateLocalOrder(worker, workerLocalOrder);
        List<OrderSparePart> orderSparePartList = localOrder.getItems();

        Optional<OrderSparePart> existingOrderSparePart = localOrder.getItems().stream().filter(item-> item.getId().equals(sparePartId)).findFirst();


        if(existingOrderSparePart.isPresent()){
            Integer actualSparePartQuantity = existingOrderSparePart.get().getQuantity();
            existingOrderSparePart.get().setQuantity(actualSparePartQuantity + localOrderRequest.quantity());
        }
        else {
            OrderSparePart orderSparePart = orderSparePartService.createOrderSparePart(
                    new OrderSparePart(
                            sparePart,
                            localOrderRequest.quantity()
                    )
            );
            orderSparePartList.add(orderSparePart);
        }

        localOrder.setItems(orderSparePartList);
        localOrderRepository.save(localOrder);



        return localOrder.getId();

    }
    private LocalOrder getOrCreateLocalOrder(Worker worker, LocalOrder workerLocalOrder) {
        if (workerLocalOrder != null && workerLocalOrder.getOrderStatus() == OrderStatus.CREATING) {
            return workerLocalOrder;
        } else {
            return createLocalOrder(worker.getId());
        }
    }

    public LocalOrder getLocalOrderById(Long localOrderId){
        return localOrderRepository.findById(localOrderId).orElseThrow(()->
                new ResourceNotFoundException("LocalOrder with id [%s] not found".formatted(localOrderId))
        );
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

        }else if(localOrderRequest.orderStatus() == OrderStatus.CANCELLED ){

            updateLocalOrder.setReceiveDate(LocalDateTime.now());
            updateLocalOrder.setOrderStatus(OrderStatus.CANCELLED);

            LocalOrder localOrder = getLocalOrderById(localOrderId);
            warehouse = Warehouse.getInstance(sparePartService);
            warehouse.releaseSparePartListFromOrder(localOrder);

        }

        localOrderRepository.save(updateLocalOrder);
    }

    public void deleteSparePartFromLocalOrder(Long sparePartId, String email){
        final Worker worker = workerService.getWorkerByEmail(email);
        LocalOrder workerLocalOrder = getLocalOrderByWorkerId(worker.getId());

        warehouse = Warehouse.getInstance(sparePartService);

        List<OrderSparePart> orderSparePartList = warehouse.deleteSparePart(workerLocalOrder.getItems(), sparePartId);
        workerLocalOrder.setItems(orderSparePartList);
        localOrderRepository.save(workerLocalOrder);
    }

}

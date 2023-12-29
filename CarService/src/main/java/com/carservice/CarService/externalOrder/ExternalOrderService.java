package com.carservice.CarService.externalOrder;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemService;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerService;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
//@Service
//public class ExternalOrderService {
//
//    private final ExternalOrderRepository externalOrderRepository;
//
//
//    public List<ExternalOrder> getAllExternalOrders(){
//        return externalOrderRepository.findAll();
//    }
//
//    private ExternalOrder createExternalOrder(Long)
//
//    public Long addOrderItemToExternalOrder(Long orderItemId, CreateExternalOrderRequest externalOrderRequest){
//        ExternalOrder externalOrder;
//
//        if(externalOrderRequest.externalOrderId() == null){
//            externalOrder =
//        }
//    }
//}

@RequiredArgsConstructor
@Service
public class ExternalOrderService {
    private final ExternalOrderRepository externalOrderRepository;
    private final WorkerService workerService;
    private final OrderItemService orderItemService;
    private final ProducerService producerService;
//    private final OrderSparePartService orderSparePartService;

    private final ExternalOrderMapper externalOrderMapper;


    public List<ExternalOrderDTO> getAllExternalOrders() {
        List<ExternalOrder> externalOrders = externalOrderRepository.findAll();
        return externalOrders.stream().map(externalOrderMapper::map).collect(Collectors.toList());
    }

    // tworzy zamówienie
    private ExternalOrder createExternalOrder(Long workerId) {
        Worker worker = workerService.getWorkerEntityById(workerId);
        ExternalOrder externalOrder = new ExternalOrder(worker, LocalDateTime.now());

        ExternalOrder saved = externalOrderRepository.save(externalOrder);
        externalOrder.submitOrder();
        return saved;
    }


    // TODO change 'add item functionality' if workes has open order with status NEW, just add item to it without providing externalOrderId
    public Long addItemToExternalOrder(CreateExternalOrderRequest externalOrderRequest, OrderItemDTO orderItemDTO) {


        System.out.println("addItemToExternalOrder: " + externalOrderRequest);

        // patrzymy czy istnieje zamówienie o podanym ID
        // sprawdzić czy Dany worker ma już external order w stanie NEW
        ExternalOrder externalOrder;
        if (externalOrderRequest.externalOrderId() == null) {
            externalOrder = createExternalOrder(externalOrderRequest.workerId());
        } else {
            //  zmień żeby szukało po emailu workera
            final Long finalExternalOrderId = externalOrderRequest.externalOrderId();
            externalOrder = externalOrderRepository.findById(finalExternalOrderId)
                    .orElseThrow(() -> new ResourceNotFoundException("External order with id [%] not found".formatted(finalExternalOrderId)));
        }

        // dorzucenie części zamiennej do listy
        List<OrderItem> tmpList = externalOrder.getItems();
        Producer producer = null;
        if(orderItemDTO.producerId() != null)
            producer = producerService.getProducerById(orderItemDTO.producerId());


        OrderItem orderItem = orderItemService.createOrderItem(
                new OrderItem(
                    orderItemDTO.sparePartName(),
                    orderItemDTO.price(),
                    orderItemDTO.quantity(),
                    producer,
                    orderItemDTO.wholesaler()
                )
        );
        tmpList.add(orderItem);

        // dodanie częśći zamiennej do listy
        externalOrder.setItems(tmpList);
        externalOrderRepository.save(externalOrder);

        return externalOrder.getId();
    }

//    public void updateExternalOrder(Long externalOrderId, UpdateExternalOrderRequest externalOrderRequest) {
//        ExternalOrder updateExternalOrder = externalOrderRepository.findById(externalOrderId)
//                .orElseThrow(() -> new ResourceNotFoundException("ExternalOrder with id [%s] not found.".formatted(externalOrderId)));
//
//        externalOrderRepository.save(updateExternalOrder);
//    }
}
package com.carservice.CarService.externalOrder;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemService;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerService;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.warehouse.Warehouse;
import com.carservice.CarService.wholesaler.WholesalerAdapterIPARTS;
import com.carservice.CarService.wholesaler.WholesalerAdapterSTARTHURT;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExternalOrderService {
    private final ExternalOrderRepository externalOrderRepository;
    private final WorkerService workerService;
    private final OrderItemService orderItemService;
    private final ProducerService producerService;
    private final WholesalerAdapterIPARTS wholesalerAdapterIPARTS;
    private final WholesalerAdapterSTARTHURT wholesalerAdapterSTARTHURT;
    private final SparePartService sparePartService;
    private final ExternalOrderMapper externalOrderMapper;

    private Warehouse warehouse;


    public List<ExternalOrderDTO> getAllExternalOrders() {
        List<ExternalOrder> externalOrders = externalOrderRepository.findAll();
        return externalOrders.stream().map(externalOrderMapper::map).collect(Collectors.toList());
    }

    public ExternalOrder getExternalOrderByWorkerId(Long workerId) {
        List<ExternalOrder> externalOrders = externalOrderRepository.findByWorkerId(workerId);
        if(externalOrders.isEmpty())
            return null;
        return externalOrders.get(0);
    }

    private ExternalOrder createExternalOrder(Long workerId) {
        Worker worker = workerService.getWorkerEntityById(workerId);
        ExternalOrder externalOrder = new ExternalOrder(worker, LocalDateTime.now());

        //        externalOrder.submitOrder();
        return externalOrderRepository.save(externalOrder);
    }

    public Long addItemToExternalOrder(CreateExternalOrderRequest externalOrderRequest, OrderItemDTO orderItemDTO) {
        final Worker worker = workerService.getWorkerByEmail(externalOrderRequest.email());
        ExternalOrder workerExternalOrder = getExternalOrderByWorkerId(worker.getId());

        if((orderItemDTO.quantity() - externalOrderRequest.quantity()) < 0) {
            throw new ResourceNotFoundException("Order Item with id [%s] not available".formatted(orderItemDTO.id()));
        }

        ExternalOrder externalOrder = getOrCreateExternalOrder(worker, workerExternalOrder);

        List<OrderItem> orderItems = externalOrder.getItems();
        Producer producer = getProducer(orderItemDTO);

        Optional<OrderItem> existingOrderItem = externalOrder.getItems().stream()
                .filter(item -> item.getExternalOrderItemId().equals(orderItemDTO.id()))
                .findFirst();

        if (existingOrderItem.isPresent()) {
            existingOrderItem.get().setQuantity(existingOrderItem.get().getQuantity() + externalOrderRequest.quantity());
        } else {
            OrderItem orderItem = orderItemService.createOrderItem(
                    new OrderItem(
                            orderItemDTO.sparePartName(),
                            orderItemDTO.price(),
                            externalOrderRequest.quantity(),
                            producer,
                            orderItemDTO.wholesaler(),
                            orderItemDTO.id()
                    )
            );
            orderItems.add(orderItem);
        }

        externalOrder.setItems(orderItems);
        externalOrderRepository.save(externalOrder);

        updateWholesalers(orderItemDTO, externalOrderRequest);

        return externalOrder.getId();
    }

    private void updateWholesalers(OrderItemDTO orderItemDTO, CreateExternalOrderRequest externalOrderRequest) {
        if(Objects.equals(orderItemDTO.wholesaler(), "IPART")) {
            wholesalerAdapterIPARTS.put(orderItemDTO.id(), externalOrderRequest.quantity());
        }
        else if(Objects.equals(orderItemDTO.wholesaler(), "STARTHURT")){
            wholesalerAdapterSTARTHURT.put(orderItemDTO.id(), externalOrderRequest.quantity());
        }
    }

    private ExternalOrder getOrCreateExternalOrder(Worker worker, ExternalOrder workerExternalOrder) {
        if (workerExternalOrder != null && workerExternalOrder.getOrderStatus() == OrderStatus.CREATING) {
            return workerExternalOrder;
        } else {
            return createExternalOrder(worker.getId());
        }
    }

    private Producer getProducer(OrderItemDTO orderItemDTO) {
        Producer producer = null;
        if (orderItemDTO.producerId() != null) {
            producer = producerService.getProducerById(orderItemDTO.producerId());
        }
        return producer;
    }

    public void updateExternalOrder(Long externalOrderId, UpdateExternalOrder externalOrderRequest){
        ExternalOrder updateExternalOrder = externalOrderRepository.findById(externalOrderId)
                .orElseThrow(()-> new ResourceNotFoundException("ExternalOrder with id [%s] not found.".formatted(externalOrderId)));

        int currentState = updateExternalOrder.getOrderStatus().getValue();

        if(externalOrderRequest.orderStatus() == null)
            return;
        if(externalOrderRequest.orderStatus() == OrderStatus.NEW && currentState < OrderStatus.NEW.getValue()){

            updateExternalOrder.setOrderStatus(OrderStatus.NEW);

        }else  if(externalOrderRequest.orderStatus() == OrderStatus.IN_PROGRESS && currentState < OrderStatus.IN_PROGRESS.getValue()){

            updateExternalOrder.setOrderStatus(OrderStatus.IN_PROGRESS);

        } else if(externalOrderRequest.orderStatus() == OrderStatus.COMPLETED && currentState < OrderStatus.COMPLETED.getValue()){

            List<OrderItem> orderItemList = updateExternalOrder.getItems();
            warehouse = Warehouse.getInstance(sparePartService);
            warehouse.addSparePart(orderItemList);

            updateExternalOrder.setOrderStatus(OrderStatus.COMPLETED);
            updateExternalOrder.setReceiveDate(LocalDateTime.now());

        }else if(externalOrderRequest.orderStatus() == OrderStatus.CANCELLED ){

            updateExternalOrder.setReceiveDate(LocalDateTime.now());
            updateExternalOrder.setOrderStatus(OrderStatus.CANCELLED);




        }

        externalOrderRepository.save(updateExternalOrder);

    }

    public ExternalOrderDTO getExternalOrderByWorkerEmail(String workerEmail) {
        Worker worker = workerService.getWorkerByEmail(workerEmail);

       ExternalOrder externalOrder = getExternalOrderByWorkerId(worker.getId());
       if(externalOrder != null && externalOrder.getOrderStatus() == OrderStatus.CREATING) {
           return externalOrderMapper.map(externalOrder);
       }
       return null;
    }
}
package com.carservice.CarService.localOrder;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalOrderService {
    private final LocalOrderRepository localOrderRepository;
    private final WorkerService workerService;
    private final SparePartService sparePartService;
    private final OrderSparePartService orderSparePartService;

    private final LocalOrderMapper localOrderMapper;
    public List<LocalOrderDTO> getAllLocalOrders(){

        List<LocalOrder> localOrder = localOrderRepository.findAll();

        return localOrder.stream().map(localOrderMapper::map).collect(Collectors.toList());
    }

    private LocalOrder createLocalOrder(Long workerId){
        Worker worker = workerService.getWorkerEntityById(workerId);
        LocalOrder localOrder = new LocalOrder(worker, LocalDateTime.now());

        LocalOrder saved = localOrderRepository.save(localOrder);
        localOrder.submitOrder();
        return saved;
    }

    public Long addItemToLocalOrder(Long sparePartId,CreateLocalOrderRequest localOrderRequest){
        LocalOrder localOrder;
        if(localOrderRequest.localOrderId() == null){
            localOrder = createLocalOrder(localOrderRequest.workerId());
        }else{
            final Long finalLocalOrderId = localOrderRequest.localOrderId();
            localOrder = localOrderRepository.findById(finalLocalOrderId)
                    .orElseThrow(()-> new ResourceNotFoundException("Local order with id [%s] not found".formatted(finalLocalOrderId)));
        }

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

        localOrderRepository.save(updateLocalOrder);
    }

}

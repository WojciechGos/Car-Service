package com.carservice.CarService.wholesaler;

import com.carservice.CarService.externalOrder.CreateExternalOrderRequest;
import com.carservice.CarService.externalOrder.ExternalOrderService;
import com.carservice.CarService.orderItem.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/wholesalers")
public class WholesalerController {
    private final WholesalerAdapterIPARTS wholesalerAdapterIPARTS;
    private final WholesalerAdapterSTARTHURT wholesalerAdapterSTARTHURT;
    private final ExternalOrderService externalOrderService;
    @GetMapping("/ipart")
    public ResponseEntity<List<OrderItemDTO>> getAllItemsFromIPART(){
        List<OrderItemDTO> requestItemDTOList = wholesalerAdapterIPARTS.getOrderItemList();
        return new ResponseEntity<>(requestItemDTOList, HttpStatus.OK);
    }

    @PostMapping("/ipart/{id}")
    public ResponseEntity<Long> addItemToExternalOrder(
            @PathVariable("id") Long id,
            @RequestBody CreateExternalOrderRequest externalOrderRequest
    ){
        OrderItemDTO orderItemDTO = wholesalerAdapterIPARTS.orderItem(id);
        Long saved = externalOrderService.addItemToExternalOrder(externalOrderRequest, orderItemDTO);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/starthurt")
    public ResponseEntity<List<OrderItemDTO>> getAllItemsFromSTARTHURT(){
        List<OrderItemDTO> requestItemDTOList = wholesalerAdapterSTARTHURT.getOrderItemList();
        return new ResponseEntity<>(requestItemDTOList, HttpStatus.OK);
    }

    @PostMapping("/starthurt/{id}")
    public ResponseEntity<Long> addItemToExternalOrderSTARTHURT(
            @PathVariable("id") Long id,
            @RequestBody CreateExternalOrderRequest externalOrderRequest
    ){
        OrderItemDTO orderItemDTO = wholesalerAdapterSTARTHURT.orderItem(id);
        Long saved = externalOrderService.addItemToExternalOrder(externalOrderRequest, orderItemDTO);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}

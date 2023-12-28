package com.carservice.CarService.wholesaler;

import com.carservice.CarService.externalOrder.CreateExternalOrderRequest;
import com.carservice.CarService.externalOrder.ExternalOrderService;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemMapper;
import com.carservice.CarService.requestItem.RequestItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/wholesaler")
public class WholesalerController {
    private final WholesalerAdapterIPARTS wholesalerAdapterIPARTS;
    private final OrderItemMapper orderItemMapper;
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
}

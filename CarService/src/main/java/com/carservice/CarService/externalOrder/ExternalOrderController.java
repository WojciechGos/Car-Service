package com.carservice.CarService.externalOrder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/order/external")
public class ExternalOrderController {

    private final ExternalOrderService externalOrderService;

    @GetMapping
    public ResponseEntity<List<ExternalOrderDTO>> getExternalOrders(){
        List<ExternalOrderDTO> externalOrders =  externalOrderService.getAllExternalOrders();
        return new ResponseEntity<>(externalOrders, HttpStatus.OK);
    }

//    @PostMapping("/item")
//    public ResponseEntity<Long> addItemToExternalOrder(
//            @RequestBody CreateExternalOrderRequest externalOrderRequest
//    ){
//        Long saved = externalOrderService.addItemToExternalOrder(externalOrderRequest );
//        return new ResponseEntity<>(saved, HttpStatus.CREATED );
//    }
}

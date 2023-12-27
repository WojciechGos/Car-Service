package com.carservice.CarService.localOrder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/order/local")
public class LocalOrderController {
    private final LocalOrderService localOrderService;

    @GetMapping
    public ResponseEntity<List<LocalOrderDTO>> getLocalOrders(){
        List<LocalOrderDTO> localOrders = localOrderService.getAllLocalOrders();
        return new ResponseEntity<>(localOrders, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Long> createLocalOrder(@RequestBody CreateLocalOrderRequest localOrderRequest){
//        Long savedLocalOrder = localOrderService.createLocalOrder(localOrderRequest);
//        return new ResponseEntity<>(savedLocalOrder, HttpStatus.CREATED);
//    }

    @PostMapping("/item/{sparePartID}")
    public ResponseEntity<Long> addItemToLocalOrder(
            @PathVariable("sparePartID") Long sparePartId,
            @RequestBody CreateLocalOrderRequest localOrderRequest){
        Long savedLocalOrder = localOrderService.addItemToLocalOrder(sparePartId, localOrderRequest);
        return new ResponseEntity<>(savedLocalOrder, HttpStatus.CREATED);
    }

    @PutMapping({"id"})
    public ResponseEntity<HttpStatus> updateLocalOrder(
            @PathVariable("id") Long localOrderId,
            @RequestBody UpdateLocalOrderRequest localOrderRequest
    ){
        localOrderService.updateLocalOrder(localOrderId, localOrderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

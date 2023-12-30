package com.carservice.CarService.externalOrder;

import com.carservice.CarService.localOrder.UpdateLocalOrderRequest;
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
    public ResponseEntity<List<ExternalOrderDTO>> getExternalOrders() {
        List<ExternalOrderDTO> externalOrders =  externalOrderService.getAllExternalOrders();
        return new ResponseEntity<>(externalOrders, HttpStatus.OK);
    }

    @PutMapping("{orderId}")
    public ResponseEntity<HttpStatus> updateExternalOrderStatus(
            @PathVariable("orderId") Long externalOrderId,
            @RequestBody UpdateExternalOrder externalOrder
    ){
        externalOrderService.updateExternalOrder(externalOrderId, externalOrder);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

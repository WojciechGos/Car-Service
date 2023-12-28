package com.carservice.CarService.wholesaler;

import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.requestItem.RequestItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/wholesaler")
public class WholesalerController {
    private final WholesalerAdapterIPARTS wholesalerAdapterIPARTS;
    @GetMapping("/ipart")
    public ResponseEntity<List<OrderItemDTO>> getAllItemsFromIPART(){
        List<OrderItemDTO> requestItemDTOList = wholesalerAdapterIPARTS.getOrderItemList();
        return new ResponseEntity<>(requestItemDTOList, HttpStatus.OK);
    }
}

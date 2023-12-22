package com.carservice.CarService.commission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/commissions")
public class CommissionController {

    private final CommissionService commissionService;

    @GetMapping
    public ResponseEntity<List<Commission>> getAllCommissions(){
        List<Commission> commissions = commissionService.getAllCommissions();
        return new ResponseEntity<>(commissions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCommission(@RequestBody CreateCommissionRequest createCommissionRequest){
        Long savedCommission = commissionService.createCommission(createCommissionRequest);
        return new ResponseEntity<>(savedCommission, HttpStatus.CREATED);
    }

}

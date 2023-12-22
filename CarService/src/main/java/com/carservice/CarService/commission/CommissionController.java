package com.carservice.CarService.commission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{id}")
    public ResponseEntity<Commission> getCommissionById(@PathVariable("id") Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);
        return new ResponseEntity<>(commission, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCommission(@RequestBody CreateCommissionRequest createCommissionRequest){
        Long savedCommission = commissionService.createCommission(createCommissionRequest);
        return new ResponseEntity<>(savedCommission, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCommission(
            @PathVariable("id") Long commissionId,
            @RequestBody UpdateCommissionRequest updateCommissionRequest
    ) {
        commissionService.updateCommission(commissionId, updateCommissionRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCommission(@PathVariable("id") Long commissionId) {
        commissionService.deleteCommission(commissionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

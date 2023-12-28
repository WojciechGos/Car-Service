package com.carservice.CarService.cost;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/costs")
public class CostController {
    private final CostService costService;
    @GetMapping
    public ResponseEntity<List<Cost>> getAllCosts() {
        List<Cost> clients = costService.getAllCosts();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cost> getCostById(@PathVariable("id") Long costId) {
        Cost cost = costService.getCostById(costId);
        return new ResponseEntity<>(cost, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCost(@RequestBody CostRequest costRequest) {
        Long savedCostId = costService.createCost(costRequest);
        return new ResponseEntity<>(savedCostId, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCost(
            @PathVariable("id") Long costId,
            @RequestBody CostRequest costRequest
    ) {
        costService.updateCost(costId, costRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCost(@PathVariable("id") Long costId) {
        costService.deleteCost(costId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

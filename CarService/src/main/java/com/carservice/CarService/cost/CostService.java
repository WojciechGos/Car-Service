package com.carservice.CarService.cost;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CostService {
    private final CostRepository costRepository;
    private final SparePartService sparePartService;

    public List<Cost> getAllCosts() {
        return costRepository.findAll();
    }

    public Cost getCostById(Long costId) {
        return costRepository.findById(costId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cost with id [%s] not found.".formatted(costId)
                ));
    }


    public Long createCost(CostRequest costRequest) {
        List<OrderSparePart> spareParts = new ArrayList<>();

        OrderSparePart orderSparePart;
        for (Map.Entry<Long, Integer> entry : costRequest.sparePartQuantities().entrySet()) {
            SparePart sparePart = getSparePartById(entry.getKey());
            if (sparePart != null) {
                orderSparePart = new OrderSparePart(sparePart, entry.getValue());
            }
        }

        Cost cost = new Cost(
                costRequest.name(),
                costRequest.createDate(),
                spareParts,
                costRequest.laborPrice(),
                costRequest.totalCost()
        );

        Cost createdCost = costRepository.save(cost);
        return createdCost.getId();
    }

    public void updateCost(Long costId, CostRequest costRequest) {
        Cost updatedCost = costRepository.findById(costId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cost with id [%s] not found.".formatted(costId)
                ));

        if(costRequest.name() != null) {
            updatedCost.setName(costRequest.name());
        }

        if(costRequest.createDate() != null) {
            updatedCost.setCreateDate(costRequest.createDate());
        }

        if(!costRequest.sparePartsIds().isEmpty()) {
            List<SparePart> spareParts = costRequest.sparePartsIds().stream()
                    .map(sparePartService::getSparePartById)
                    .collect(Collectors.toList());
            updatedCost.setSpareParts(spareParts);
        }

        if(costRequest.laborPrice() != null) {
            updatedCost.setLaborPrice(costRequest.laborPrice());
        }

        if(costRequest.totalCost() != null) {
            updatedCost.setTotalCost(costRequest.totalCost());
        }

        costRepository.save(updatedCost);
    }

    public void deleteCost(Long costId) {
        costRepository.deleteById(costId);
    }
}

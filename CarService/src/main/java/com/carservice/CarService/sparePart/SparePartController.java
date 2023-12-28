package com.carservice.CarService.sparePart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/spareParts")
public class SparePartController {
    private final SparePartService sparePartService;
    @GetMapping
    public ResponseEntity<List<SparePart>> getAllSpareParts() {
        List<SparePart> spareParts = sparePartService.getAllSpareParts();
        return new ResponseEntity<>(spareParts, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SparePart> getSparePartById(@PathVariable("id") Long sparePartId) {
        SparePart sparePart = sparePartService.getSparePartById(sparePartId);
        return new ResponseEntity<>(sparePart, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateSparePart(
            @PathVariable("id") Long sparePartId,
            @RequestBody SparePart sparePart
    ) {
        sparePartService.updateSparePart(sparePartId, sparePart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
package com.carservice.CarService.methodsTests;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.sparePart.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.carservice.CarService.item.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SparePartServiceTest {

    @Mock
    private SparePartRepository sparePartRepository;

    @InjectMocks
    private SparePartService sparePartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllSpareParts() {
        // Mock data
        List<SparePart> spareParts = new ArrayList<>();
        when(sparePartRepository.findAll()).thenReturn(spareParts);

        // Call the method
        List<SparePart> result = sparePartService.getAllSpareParts();

        // Assertions
        assertEquals(spareParts, result);
    }

    @Test
    void getSparePartById_existingId() {
        // Mock data
        Long sparePartId = 1L;
        SparePart sparePart = new SparePart();
        when(sparePartRepository.findById(sparePartId)).thenReturn(Optional.of(sparePart));

        // Call the method
        SparePart result = sparePartService.getSparePartById(sparePartId);

        // Assertions
        assertNotNull(result);
        assertEquals(sparePart, result);
    }

    @Test
    void getSparePartById_nonExistingId() {
        // Mock data
        Long sparePartId = 1L;
        when(sparePartRepository.findById(sparePartId)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> sparePartService.getSparePartById(sparePartId));
    }

    @Test
    void updateSparePartById_existingId() {
        // Mock data
        Long sparePartId = 1L;
        Producer producer = new Producer();
        producer.setName("producer");

        SparePart existingSparePart = new SparePart();
        existingSparePart.setId(sparePartId);
        when(sparePartRepository.findById(sparePartId)).thenReturn(Optional.of(existingSparePart));

        SparePart updatedSparePart = new SparePart();
        updatedSparePart.setName("Updated Part");
        updatedSparePart.setPrice(BigDecimal.valueOf(50.0));
        updatedSparePart.setQuantity(10);
        updatedSparePart.setProducer(producer);

        // Call the method
        sparePartService.updateSparePart(sparePartId, updatedSparePart);

        // Assertions
        verify(sparePartRepository, times(1)).save(existingSparePart);
        assertEquals("Updated Part", existingSparePart.getName());
        assertEquals(BigDecimal.valueOf(50.0), existingSparePart.getPrice());
        assertEquals(10, existingSparePart.getQuantity());
        assertEquals(producer, existingSparePart.getProducer());
    }

    @Test
    void updateSparePartById_nonExistingId() {
        // Mock data
        Long sparePartId = 1L;
        when(sparePartRepository.findById(sparePartId)).thenReturn(Optional.empty());

        SparePart updatedSparePart = new SparePart();

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> sparePartService.updateSparePart(sparePartId, updatedSparePart));
    }

    @Test
    void createSparePart() {
        // Mock data
        Producer producer = new Producer();
        producer.setName("Name");

        SparePart newSparePart = new SparePart();
        newSparePart.setName("New Part");
        newSparePart.setPrice(BigDecimal.valueOf(30.0));
        newSparePart.setQuantity(5);
        newSparePart.setProducer(producer);

        // Call the method
        sparePartService.createSparePart(newSparePart);

        // Assertions
        verify(sparePartRepository, times(1)).save(newSparePart);
    }
}

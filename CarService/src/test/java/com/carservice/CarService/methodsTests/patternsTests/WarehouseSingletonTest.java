package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.sparePart.SparePartService;
import com.carservice.CarService.warehouse.Warehouse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseSingletonTest {

    @Test
    void getInstance_ShouldReturnSameInstance() {
        // Arrange
        SparePartService mockSparePartService = Mockito.mock(SparePartService.class);

        // Act
        Warehouse instance1 = Warehouse.getInstance(mockSparePartService);
        Warehouse instance2 = Warehouse.getInstance(mockSparePartService);

        // Assert
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    void getInstance_ShouldBeThreadSafe() {
        // This test checks if the getInstance method is thread-safe.

        // Arrange
        SparePartService mockSparePartService = Mockito.mock(SparePartService.class);

        // Act
        // Run multiple threads to get instances concurrently
        Warehouse[] instances = new Warehouse[10];
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> instances[finalI] = Warehouse.getInstance(mockSparePartService));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Assert
        // Ensure that all instances are the same (singleton pattern)
        for (int i = 1; i < 10; i++) {
            assertSame(instances[i - 1], instances[i], "All instances should be the same");
        }
    }
}

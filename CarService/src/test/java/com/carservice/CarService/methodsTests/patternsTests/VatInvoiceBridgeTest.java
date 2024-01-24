package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.commission.*;
import com.carservice.CarService.printInvoice.PrintHTMLInvoice;
import com.carservice.CarService.printInvoice.PrintInvoice;
import com.carservice.CarService.vatInvoice.VatInvoice;
import com.carservice.CarService.vatInvoice.VatInvoiceRepository;
import com.carservice.CarService.vatInvoice.VatInvoiceService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

class VatInvoiceBridgeTest {

    @Mock
    private VatInvoiceRepository vatInvoiceRepository;

    @Mock
    private Commission commission;

    @InjectMocks
    private VatInvoiceService vatInvoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateVatInvoicePDF() {
        // Arrange
        VatInvoice vatInvoice = new VatInvoice(new PrintHTMLInvoice());
        vatInvoice.setCommission(commission);

        when(vatInvoiceRepository.save(any())).thenReturn(vatInvoice);
        when(vatInvoiceRepository.findById(any())).thenReturn(java.util.Optional.of(vatInvoice));

        // Act
        byte[] result = vatInvoiceService.generateVatInvoicePDF(1L);

        // Assert
        assertArrayEquals("Generated PDF".getBytes(), result);

        // Verify interactions
        verify(vatInvoiceRepository, times(1)).save(any());
        verify(vatInvoiceRepository, times(1)).findById(any());
        }

    @Test
    void generateVatInvoiceHTML() {
        // Arrange
        VatInvoice vatInvoice = new VatInvoice(new PrintHTMLInvoice());
        vatInvoice.setCommission(commission);

        when(vatInvoiceRepository.save(any())).thenReturn(vatInvoice);
        when(vatInvoiceRepository.findById(any())).thenReturn(java.util.Optional.of(vatInvoice));

        // Act
        byte[] result = vatInvoiceService.generateVatInvoiceHTML(1L);

        // Assert
        assertArrayEquals("Generated HTML".getBytes(), result);

        // Verify interactions
        verify(vatInvoiceRepository, times(1)).save(any());
        verify(vatInvoiceRepository, times(1)).findById(any());
     }
}

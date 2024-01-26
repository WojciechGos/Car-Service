package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.commission.*;
import com.carservice.CarService.saleInvoice.*;
import com.carservice.CarService.printInvoice.PrintHTMLInvoice;
import com.carservice.CarService.printInvoice.PrintPDFInvoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SaleInvoiceBridgeTest {

    @Mock
    private SaleInvoiceRepository saleInvoiceRepository;

    @Mock
    private CommissionService commissionService;

    @InjectMocks
    private SaleInvoiceService saleInvoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllSaleInvoices() {
        // Arrange
        SaleInvoice saleInvoice1 = new SaleInvoice(new PrintPDFInvoice());
        SaleInvoice saleInvoice2 = new SaleInvoice(new PrintHTMLInvoice());
        List<SaleInvoice> expectedSaleInvoices = Arrays.asList(saleInvoice1, saleInvoice2);

        when(saleInvoiceRepository.findAll()).thenReturn(expectedSaleInvoices);

        // Act
        List<SaleInvoice> result = saleInvoiceService.getAllSaleInvoices();

        // Assert
        assertEquals(expectedSaleInvoices, result);
    }

    @Test
    void getSaleInvoicesByCommissionId() {
        // Arrange
        Long commissionId = 1L;
        SaleInvoice saleInvoice1 = new SaleInvoice(new PrintPDFInvoice());
        SaleInvoice saleInvoice2 = new SaleInvoice(new PrintHTMLInvoice());
        List<SaleInvoice> expectedSaleInvoices = Arrays.asList(saleInvoice1, saleInvoice2);

        when(saleInvoiceRepository.findByCommissionId(commissionId)).thenReturn(expectedSaleInvoices);

        // Act
        List<SaleInvoice> result = saleInvoiceService.getSaleInvoicesByCommissionId(commissionId);

        // Assert
        assertEquals(expectedSaleInvoices, result);
    }
}

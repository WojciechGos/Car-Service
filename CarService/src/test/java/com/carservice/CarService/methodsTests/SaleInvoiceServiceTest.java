package com.carservice.CarService.methodsTests;
import com.carservice.CarService.commission.*;
import com.carservice.CarService.saleInvoice.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleInvoiceServiceTest {


    @Test
    void getAllSaleInvoices() {
        SaleInvoiceRepository saleInvoiceRepository = Mockito.mock(SaleInvoiceRepository.class);
        CommissionService commissionService = Mockito.mock(CommissionService.class);
        SaleInvoiceService saleInvoiceService = new SaleInvoiceService(saleInvoiceRepository, commissionService);

        List<SaleInvoice> expectedSaleInvoices = Collections.singletonList(new SaleInvoice());
        when(saleInvoiceRepository.findAll()).thenReturn(expectedSaleInvoices);

        List<SaleInvoice> actualSaleInvoices = saleInvoiceService.getAllSaleInvoices();

        assertEquals(expectedSaleInvoices, actualSaleInvoices);
        verify(saleInvoiceRepository, times(1)).findAll();
    }

    @Test
    void getSaleInvoicesByCommissionId() {
        SaleInvoiceRepository saleInvoiceRepository = Mockito.mock(SaleInvoiceRepository.class);
        CommissionService commissionService = Mockito.mock(CommissionService.class);
        SaleInvoiceService saleInvoiceService = new SaleInvoiceService(saleInvoiceRepository, commissionService);

        Long commissionId = 1L;
        List<SaleInvoice> expectedSaleInvoices = Collections.singletonList(new SaleInvoice());

        when(saleInvoiceRepository.findByCommissionId(commissionId)).thenReturn(expectedSaleInvoices);

        List<SaleInvoice> actualSaleInvoices = saleInvoiceService.getSaleInvoicesByCommissionId(commissionId);

        assertEquals(expectedSaleInvoices, actualSaleInvoices);
        verify(saleInvoiceRepository, times(1)).findByCommissionId(commissionId);
    }
/*
    @Test
    void generateSaleInvoicePDF() {
        // Mock data
        Long commissionId = 1L;
        Commission commission = new Commission();
        when(commissionService.getCommissionById(commissionId)).thenReturn(commission);

        // Call the method
        byte[] result = saleInvoiceService.generateSaleInvoicePDF(commissionId);

        // Assertions
        assertNotNull(result);
        verify(saleInvoiceRepository, times(1)).save(any(SaleInvoice.class));
        // Add additional assertions for the generated PDF content if needed
    }

    @Test
    void generateSaleInvoiceHTML() {
        // Mock data
        Long commissionId = 1L;
        Commission commission = new Commission();
        when(commissionService.getCommissionById(commissionId)).thenReturn(commission);

        // Call the method
        byte[] result = saleInvoiceService.generateSaleInvoiceHTML(commissionId);

        // Assertions
        assertNotNull(result);
        verify(saleInvoiceRepository, times(1)).save(any(SaleInvoice.class));
        // Add additional assertions for the generated HTML content if needed
    }


 */
}

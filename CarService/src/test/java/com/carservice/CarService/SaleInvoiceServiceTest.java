package com.carservice.CarService;
import com.carservice.CarService.commission.*;
import com.carservice.CarService.saleInvoice.*;
import com.carservice.CarService.printInvoice.*;
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

   /* public byte[] generateSaleInvoicePDF(Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);

        SaleInvoice saleInvoice = new SaleInvoice(new PrintPDFInvoice());
        saleInvoice.setCommission(commission);

        saleInvoiceRepository.save(saleInvoice);


        String clientName = (commission.getClient() != null) ? commission.getClient().getName() : "Unknown";

        return saleInvoice.generateInvoice(commission, clientName);
    }

    public byte[] generateSaleInvoiceHTML(Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);

        SaleInvoice saleInvoice = new SaleInvoice(new PrintHTMLInvoice());
        saleInvoice.setCommission(commission);

        saleInvoiceRepository.save(saleInvoice);


        String clientName = (commission.getClient() != null) ? commission.getClient().getName() : "Unknown";

        return saleInvoice.generateInvoice(commission, clientName);
    }

    */

}

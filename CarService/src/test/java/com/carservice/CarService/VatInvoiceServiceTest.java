package com.carservice.CarService;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionService;
import com.carservice.CarService.printInvoice.PrintHTMLInvoice;
import com.carservice.CarService.printInvoice.PrintPDFInvoice;
import com.carservice.CarService.vatInvoice.VatInvoice;
import com.carservice.CarService.vatInvoice.VatInvoiceRepository;
import com.carservice.CarService.vatInvoice.VatInvoiceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class VatInvoiceServiceTest {

    @Mock
    private VatInvoiceRepository vatInvoiceRepository;

    @Mock
    private CommissionService commissionService;

    @InjectMocks
    private VatInvoiceService vatInvoiceService;

    @Test
    void generateVatInvoicePDF() {

        Long commissionId = 1L;
        Commission commission = new Commission();
        VatInvoice vatInvoice = new VatInvoice(new PrintPDFInvoice());
        vatInvoice.setCommission(commission);

        when(commissionService.getCommissionById(commissionId)).thenReturn(commission);
        when(vatInvoiceRepository.save(any(VatInvoice.class))).thenReturn(vatInvoice);


        byte[] result = vatInvoiceService.generateVatInvoicePDF(commissionId);


        verify(commissionService, times(1)).getCommissionById(commissionId);
        verify(vatInvoiceRepository, times(1)).save(any(VatInvoice.class));


        assertEquals(0, result.length);
    }
/*
    @Test
    void generateVatInvoiceHTML() {

        Long commissionId = 1L;
        Commission commission = new Commission();
        VatInvoice vatInvoice = new VatInvoice(new PrintHTMLInvoice());
        vatInvoice.setCommission(commission);

        when(commissionService.getCommissionById(commissionId)).thenReturn(commission);
        when(vatInvoiceRepository.save(any(VatInvoice.class))).thenReturn(vatInvoice);


        byte[] result = vatInvoiceService.generateVatInvoiceHTML(commissionId);


        verify(commissionService, times(1)).getCommissionById(commissionId);
        verify(vatInvoiceRepository, times(1)).save(any(VatInvoice.class));

        assertEquals(0, result.length);
    }

 */

}

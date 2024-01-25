package com.carservice.CarService.methodsTests.patternsTests;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionService;
import com.carservice.CarService.printInvoice.PrintHTMLInvoice;
import com.carservice.CarService.printInvoice.PrintPDFInvoice;
import com.carservice.CarService.vatInvoice.VatInvoice;
import com.carservice.CarService.vatInvoice.VatInvoiceRepository;
import com.carservice.CarService.vatInvoice.VatInvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VatInvoiceBridgeTest {

    @Mock
    private VatInvoiceRepository vatInvoiceRepository;

    @Mock
    private CommissionService commissionService;

    @InjectMocks
    private VatInvoiceService vatInvoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllVatInvoices() {
        // Arrange
        VatInvoice vatInvoice1 = new VatInvoice(new PrintPDFInvoice());
        VatInvoice vatInvoice2 = new VatInvoice(new PrintHTMLInvoice());
        List<VatInvoice> expectedVatInvoices = Arrays.asList(vatInvoice1, vatInvoice2);

        when(vatInvoiceRepository.findAll()).thenReturn(expectedVatInvoices);

        // Act
        List<VatInvoice> result = vatInvoiceService.getAllVatInvoices();

        // Assert
        assertEquals(expectedVatInvoices, result);
    }

    @Test
    void getVatInvoicesByCommissionId() {
        // Arrange
        Long commissionId = 1L;
        VatInvoice vatInvoice1 = new VatInvoice(new PrintPDFInvoice());
        VatInvoice vatInvoice2 = new VatInvoice(new PrintHTMLInvoice());
        List<VatInvoice> expectedVatInvoices = Arrays.asList(vatInvoice1, vatInvoice2);

        when(vatInvoiceRepository.findByCommissionId(commissionId)).thenReturn(expectedVatInvoices);

        // Act
        List<VatInvoice> result = vatInvoiceService.getVatInvoicesByCommissionId(commissionId);

        // Assert
        assertEquals(expectedVatInvoices, result);
    }

    @Test
    void generateVatInvoicePDF() {
        // Arrange
        Long commissionId = 1L;
        Commission commission = new Commission();
        VatInvoice vatInvoice = new VatInvoice(new PrintPDFInvoice());

        when(commissionService.getCommissionById(commissionId)).thenReturn(commission);
        when(vatInvoiceRepository.save(any(VatInvoice.class))).thenReturn(vatInvoice);

        // Act
        byte[] result = vatInvoiceService.generateVatInvoicePDF(commissionId);

        // Assert (add your specific assertions for the generated PDF)
        // assertEquals(expectedBytes, result);
    }

    @Test
    void generateVatInvoiceHTML() {
        // Arrange
        Long commissionId = 1L;
        Commission commission = new Commission();
        VatInvoice vatInvoice = new VatInvoice(new PrintHTMLInvoice());

        when(commissionService.getCommissionById(commissionId)).thenReturn(commission);
        when(vatInvoiceRepository.save(any(VatInvoice.class))).thenReturn(vatInvoice);

        // Act
        byte[] result = vatInvoiceService.generateVatInvoiceHTML(commissionId);

        // Assert (add your specific assertions for the generated HTML)
        // assertEquals(expectedBytes, result);
    }
}

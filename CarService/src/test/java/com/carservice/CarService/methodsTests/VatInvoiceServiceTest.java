package com.carservice.CarService.methodsTests;

import com.carservice.CarService.commission.CommissionService;
import com.carservice.CarService.vatInvoice.VatInvoice;
import com.carservice.CarService.vatInvoice.VatInvoiceRepository;
import com.carservice.CarService.vatInvoice.VatInvoiceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class VatInvoiceServiceTest {

    @Mock
    private VatInvoiceRepository vatInvoiceRepository;

    @Mock
    private CommissionService commissionService;

        @Test
        void getAllVatInvoices() {
            // Arrange
            VatInvoiceService vatInvoiceService = new VatInvoiceService(vatInvoiceRepository, commissionService);
            List<VatInvoice> expectedInvoices = Arrays.asList(new VatInvoice(), new VatInvoice());
            when(vatInvoiceRepository.findAll()).thenReturn(expectedInvoices);

            // Act
            List<VatInvoice> actualInvoices = vatInvoiceService.getAllVatInvoices();

            // Assert
            assertEquals(expectedInvoices, actualInvoices);
            verify(vatInvoiceRepository, times(1)).findAll();
        }

        @Test
        void getVatInvoicesByCommissionId() {
            // Arrange
            VatInvoiceService vatInvoiceService = new VatInvoiceService(vatInvoiceRepository, commissionService);
            Long commissionId = 1L;
            List<VatInvoice> expectedInvoices = Arrays.asList(new VatInvoice(), new VatInvoice());
            when(vatInvoiceRepository.findByCommissionId(commissionId)).thenReturn(expectedInvoices);

            // Act
            List<VatInvoice> actualInvoices = vatInvoiceService.getVatInvoicesByCommissionId(commissionId);

            // Assert
            assertEquals(expectedInvoices, actualInvoices);
            verify(vatInvoiceRepository, times(1)).findByCommissionId(commissionId);
        }
    }

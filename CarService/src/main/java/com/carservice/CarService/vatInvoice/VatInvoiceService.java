package com.carservice.CarService.vatInvoice;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionService;
import com.carservice.CarService.printInvoice.PrintHTMLInvoice;
import com.carservice.CarService.printInvoice.PrintPDFInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VatInvoiceService {
    private final VatInvoiceRepository vatInvoiceRepository;
    private final CommissionService commissionService;
    public List<VatInvoice> getAllVatInvoices() {
        return vatInvoiceRepository.findAll();
    }

    public List<VatInvoice> getVatInvoicesByCommissionId(Long commissionId) {
        return vatInvoiceRepository.findByCommissionId(commissionId);
    }

    public byte[] generateVatInvoicePDF(Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);

        VatInvoice vatInvoice = new VatInvoice(new PrintPDFInvoice());
        vatInvoice.setCommission(commission);

        vatInvoiceRepository.save(vatInvoice);

        return vatInvoice.generateInvoice(commission);
    }

    public byte[] generateVatInvoiceHTML(Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);

        VatInvoice vatInvoice = new VatInvoice(new PrintHTMLInvoice());
        vatInvoice.setCommission(commission);

        vatInvoiceRepository.save(vatInvoice);

        return vatInvoice.generateInvoice(commission);
    }
}

package com.carservice.CarService.saleInvoice;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionService;
import com.carservice.CarService.printInvoice.PrintHTMLInvoice;
import com.carservice.CarService.printInvoice.PrintPDFInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SaleInvoiceService {
    private final SaleInvoiceRepository saleInvoiceRepository;
    private final CommissionService commissionService;
    public List<SaleInvoice> getAllSaleInvoices() {
        return saleInvoiceRepository.findAll();
    }

    public List<SaleInvoice> getSaleInvoicesByCommissionId(Long commissionId) {
        return saleInvoiceRepository.findByCommissionId(commissionId);
    }

    public byte[] generateSaleInvoicePDF(Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);

        SaleInvoice saleInvoice = new SaleInvoice(new PrintPDFInvoice());
        saleInvoice.setCommission(commission);

        saleInvoiceRepository.save(saleInvoice);

        return saleInvoice.generateInvoice(commission);
    }

    public byte[] generateSaleInvoiceHTML(Long commissionId) {
        Commission commission = commissionService.getCommissionById(commissionId);

        SaleInvoice saleInvoice = new SaleInvoice(new PrintHTMLInvoice());
        saleInvoice.setCommission(commission);

        saleInvoiceRepository.save(saleInvoice);

        return saleInvoice.generateInvoice(commission);
    }
}

package com.carservice.CarService.saleInvoice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/invoices/sale")
public class SaleInvoiceController {
    private final SaleInvoiceService saleInvoiceService;
    @GetMapping
    public ResponseEntity<List<SaleInvoice>> getAllSaleInvoices(){
        List<SaleInvoice> saleInvoices = saleInvoiceService.getAllSaleInvoices();
        return new ResponseEntity<>(saleInvoices, HttpStatus.OK);
    }
    @GetMapping("/{commissionId}")
    public ResponseEntity<List<SaleInvoice>> getSaleInvoiceByCommissionId(@PathVariable("commissionId") Long commissionId) {
        List<SaleInvoice> saleInvoices = saleInvoiceService.getSaleInvoicesByCommissionId(commissionId);
        return new ResponseEntity<>(saleInvoices, HttpStatus.OK);
    }

    @GetMapping("/generate/pdf/{commissionId}")
    public ResponseEntity<byte[]> generateSaleInvoicePDF(@PathVariable Long commissionId) {
        byte[] bytes = saleInvoiceService.generateSaleInvoicePDF(commissionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice.pdf");

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/generate/html/{commissionId}")
    public ResponseEntity<byte[]> generateSaleInvoiceHTML(@PathVariable Long commissionId) {
        byte[] bytes = saleInvoiceService.generateSaleInvoiceHTML(commissionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}

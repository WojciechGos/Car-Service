package com.carservice.CarService.vatInvoice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/invoices/vat")
public class VatInvoiceController {
    private final VatInvoiceService vatInvoiceService;
    @GetMapping
    public ResponseEntity<List<VatInvoice>> getAllVatInvoices(){
        List<VatInvoice> vatInvoices = vatInvoiceService.getAllVatInvoices();
        return new ResponseEntity<>(vatInvoices, HttpStatus.OK);
    }
    @GetMapping("/{commissionId}")
    public ResponseEntity<List<VatInvoice>> getVatInvoiceByCommissionId(@PathVariable("commissionId") Long commissionId) {
        List<VatInvoice> vatInvoices = vatInvoiceService.getVatInvoicesByCommissionId(commissionId);
        return new ResponseEntity<>(vatInvoices, HttpStatus.OK);
    }

    @GetMapping("/generate/pdf/{commissionId}")
    public ResponseEntity<byte[]> generateVatInvoicePDF(@PathVariable Long commissionId) {
        byte[] bytes = vatInvoiceService.generateVatInvoicePDF(commissionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice.pdf");

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/generate/html/{commissionId}")
    public ResponseEntity<byte[]> generateVatInvoiceHTML(@PathVariable Long commissionId) {
        byte[] bytes = vatInvoiceService.generateVatInvoiceHTML(commissionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}

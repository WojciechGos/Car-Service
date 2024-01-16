package com.carservice.CarService.printInvoice;

import com.carservice.CarService.commission.Commission;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class PrintPDFInvoice implements PrintInvoice {
    @Override
    public byte[] generateInvoice(Commission commission) {
        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Invoice for Commission: " + commission.getId()));
            document.add(new Paragraph("Customer: " + commission.getClient().getName() + " " + commission.getClient().getSurname()));
            document.add(new Paragraph("Total Commission Value: " + commission.getTotalCost().getTotalCost() + " zl"));

            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.carservice.CarService.printInvoice;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.commission.Commission;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;

import java.io.ByteArrayOutputStream;


public class PrintPDFInvoice implements PrintInvoice {
    @Override
    public byte[] generateInvoice(Commission commission) {
        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Invoice for Commission with ID: " + commission.getId());
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph customer = new Paragraph("Customer: " + commission.getClient().getName() + " " + commission.getClient().getSurname());
            customer.setAlignment(Element.ALIGN_CENTER);
            document.add(customer);

            Paragraph totalValue = new Paragraph("Total Commission Value: " + commission.getTotalCost().getTotalCost() + " zł");
            totalValue.setAlignment(Element.ALIGN_CENTER);
            document.add(totalValue);

            document.add(new Paragraph("\n"));

            Paragraph costDetails = new Paragraph("Cost Details:");
            costDetails.setAlignment(Element.ALIGN_CENTER);
            document.add(costDetails);

            Paragraph laborPrice = new Paragraph("Labor Price: " + commission.getTotalCost().getLaborPrice() + " zł");
            laborPrice.setAlignment(Element.ALIGN_CENTER);
            document.add(laborPrice);

            List sparePartsList = new List(List.UNORDERED);
            for (OrderSparePart sparePart : commission.getTotalCost().getSpareParts()) {
                ListItem listItem = new ListItem(sparePart.getSparePart().getName() + " - " + sparePart.getSparePart().getPrice() + " zł");
                sparePartsList.add(listItem);
            }
            document.add(sparePartsList);

            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}

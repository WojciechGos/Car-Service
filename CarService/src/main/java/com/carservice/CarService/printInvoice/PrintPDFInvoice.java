package com.carservice.CarService.printInvoice;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.commission.Commission;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;


public class PrintPDFInvoice implements PrintInvoice {
    @Override
    public byte[] generateInvoice(Commission commission) {
        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add title
            Paragraph title = new Paragraph("Invoice for Commission with ID: " + commission.getId(), new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add customer information
            Paragraph customer = new Paragraph("Customer: " + commission.getClient().getName() + " " + commission.getClient().getSurname(), new Font(Font.FontFamily.TIMES_ROMAN, 18));
            customer.setAlignment(Element.ALIGN_CENTER);
            document.add(customer);

            // Add total commission value
            Paragraph totalValue = new Paragraph("Total Commission Value: " + commission.getTotalCost().getTotalCost() + " zl", new Font(Font.FontFamily.TIMES_ROMAN, 18));
            totalValue.setAlignment(Element.ALIGN_CENTER);
            document.add(totalValue);

            // Add a new line
            document.add(new Paragraph("\n"));

            // Add cost details
            Paragraph costDetails = new Paragraph("Cost Details:", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
            costDetails.setAlignment(Element.ALIGN_CENTER);
            document.add(costDetails);

            // Add labor price outside the table
            Paragraph laborPrice = new Paragraph("Labor Price: " + commission.getTotalCost().getLaborPrice() + " zl", new Font(Font.FontFamily.TIMES_ROMAN, 14));
            laborPrice.setAlignment(Element.ALIGN_CENTER);
            document.add(laborPrice);

            // Add a new line
            document.add(new Paragraph("\n"));

            // Create a table for cost details
            PdfPTable table = new PdfPTable(4); // 4 columns (Name, Quantity, Price, Total Cost)
            table.setWidthPercentage(60); // Table width as a percentage of the page width
            table.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Add column headers
            PdfPCell nameHeader = new PdfPCell(new Phrase("Name", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            nameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            nameHeader.setFixedHeight(25);
            table.addCell(nameHeader);

            PdfPCell quantityHeader = new PdfPCell(new Phrase("Quantity", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            quantityHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantityHeader.setFixedHeight(25);
            table.addCell(quantityHeader);

            PdfPCell priceHeader = new PdfPCell(new Phrase("Price", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            priceHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceHeader.setFixedHeight(25);
            table.addCell(priceHeader);

            PdfPCell totalCostHeader = new PdfPCell(new Phrase("Total Cost", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
            totalCostHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalCostHeader.setFixedHeight(25);
            table.addCell(totalCostHeader);

            // Add spare parts to the table
            for (OrderSparePart sparePart : commission.getTotalCost().getSpareParts()) {
                PdfPCell nameCell = new PdfPCell(new Phrase(sparePart.getSparePart().getName(), new Font(Font.FontFamily.TIMES_ROMAN, 14)));
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameCell.setFixedHeight(25);
                table.addCell(nameCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(sparePart.getQuantity()), new Font(Font.FontFamily.TIMES_ROMAN, 14)));
                quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityCell.setFixedHeight(25);
                table.addCell(quantityCell);

                PdfPCell priceCell = new PdfPCell(new Phrase(sparePart.getSparePart().getPrice().toString() + "zl", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
                priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceCell.setFixedHeight(25);
                table.addCell(priceCell);

                // Multiply BigDecimal by the quantity using the multiply method
                BigDecimal totalCostValue = sparePart.getSparePart().getPrice().multiply(BigDecimal.valueOf(sparePart.getQuantity()));
                PdfPCell totalCostCell = new PdfPCell(new Phrase(totalCostValue.toString() + " zl", new Font(Font.FontFamily.TIMES_ROMAN, 14)));
                totalCostCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                totalCostCell.setFixedHeight(25);
                table.addCell(totalCostCell);
            }

            // Add the table to the document
            document.add(table);

            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}

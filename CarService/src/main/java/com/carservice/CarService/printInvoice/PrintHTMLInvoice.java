package com.carservice.CarService.printInvoice;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.commission.Commission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class PrintHTMLInvoice implements PrintInvoice {
    @Override
    public byte[] generateInvoice(Commission commission) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            StringBuilder htmlContent = new StringBuilder("<html><head><style>" +
                    "body { font-family: Arial, sans-serif; }" +
                    "h2 { color: #333; }" +
                    "p { margin: 10px 0; }" +
                    "ul { list-style-type: disc; padding-left: 20px; }" +
                    "li { margin-bottom: 5px; }" +
                    "</style></head><body>" +
                    "<h2>Invoice for Commission with ID: " + commission.getId() + "</h2>" +
                    "<p>Customer: " + commission.getClient().getName() + " " + commission.getClient().getSurname() + "</p>" +
                    "<p>Total Commission Value: " + commission.getTotalCost().getTotalCost() + " zł" + "</p><br>" +
                    "<h3>Cost Details:</h3>" +
                    "<p>Labor Price: " + commission.getTotalCost().getLaborPrice() + " zł" + "</p>" +
                    "<p>Spare Parts:</p>" +
                    "<ul>");

            for (OrderSparePart sparePart : commission.getTotalCost().getSpareParts()) {
                BigDecimal totalCostValue = sparePart.getSparePart().getPrice().multiply(BigDecimal.valueOf(sparePart.getQuantity()));

                htmlContent.append("<li>").append(sparePart.getSparePart().getName()).append(" - ").append("Quantity: ").append(sparePart.getQuantity()).append(" - ").append("Total cost: ").append(totalCostValue).append(" zł</li>");
            }

            htmlContent.append("</ul>" + "</body></html>");

            outputStream.write(htmlContent.toString().getBytes());

            return outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

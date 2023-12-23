package com.carservice.CarService.printInvoice;

import com.carservice.CarService.commission.Commission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PrintHTMLInvoice implements PrintInvoice {
    @Override
    public byte[] generateInvoice(Commission commission) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            String htmlContent = "<html><body>" +
                    "<h2>Invoice for Commission: " + commission.getId() + "</h2>" +
                    "<p>Customer: " + commission.getClient().getName() + " " + commission.getClient().getSurname() + "</p>" +
                    "<p>Total Commission Value: " + commission.getTotalCost().getTotalCost() + "</p>" +
                    "</body></html>";

            outputStream.write(htmlContent.getBytes());

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

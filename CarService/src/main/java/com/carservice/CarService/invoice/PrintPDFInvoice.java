package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;

public class PrintPDFInvoice implements PrintInvoice {
    @Override
    public void generateInvoice(Commission commission) {
        System.out.println("Generating PDF invoice for commission: " + commission);
    }
}

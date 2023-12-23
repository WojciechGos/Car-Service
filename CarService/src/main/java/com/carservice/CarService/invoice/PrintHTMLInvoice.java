package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;

public class PrintHTMLInvoice implements PrintInvoice {
    @Override
    public void generateInvoice(Commission commission) {
        System.out.println("Generating HTML invoice for commission: " + commission);
    }
}

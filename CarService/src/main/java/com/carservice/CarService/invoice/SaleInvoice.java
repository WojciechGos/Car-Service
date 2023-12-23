package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;

public class SaleInvoice extends Invoice {
    public SaleInvoice(PrintInvoice printInvoice) {
        super(printInvoice);
    }

    @Override
    public void generateInvoice(Commission commission) {
        System.out.println("Generating Sale invoice");
        printInvoice.generateInvoice(commission);
    }
}

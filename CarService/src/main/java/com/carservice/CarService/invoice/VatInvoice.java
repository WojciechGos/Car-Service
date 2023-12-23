package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;

public class VatInvoice extends Invoice {
    public VatInvoice(PrintInvoice printInvoice) {
        super(printInvoice);
    }

    @Override
    public void generateInvoice(Commission commission) {
        System.out.println("Generating VAT invoice");
        printInvoice.generateInvoice(commission);
    }
}

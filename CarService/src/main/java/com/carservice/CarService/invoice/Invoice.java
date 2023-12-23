package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;

public abstract class Invoice {
    protected PrintInvoice printInvoice;
    protected Commission commission;
    public Invoice(PrintInvoice printInvoice) {
        this.printInvoice = printInvoice;
    }

    public abstract void generateInvoice(Commission commission);
}

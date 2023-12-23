package com.carservice.CarService.printInvoice;

import com.carservice.CarService.commission.Commission;

public interface PrintInvoice {
    byte[] generateInvoice(Commission commission);
}

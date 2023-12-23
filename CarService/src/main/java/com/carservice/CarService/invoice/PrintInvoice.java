package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;

public interface PrintInvoice {
    void generateInvoice(Commission commission);
}

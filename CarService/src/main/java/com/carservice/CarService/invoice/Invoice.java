package com.carservice.CarService.invoice;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.printInvoice.PrintInvoice;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public abstract class Invoice {
    @Transient
    protected PrintInvoice printInvoice;
    @ManyToOne
    @JoinColumn(name = "commission_id")
    protected Commission commission;
    public Invoice(PrintInvoice printInvoice) {
        this.printInvoice = printInvoice;
    }

    public abstract byte[] generateInvoice(Commission commission);
}

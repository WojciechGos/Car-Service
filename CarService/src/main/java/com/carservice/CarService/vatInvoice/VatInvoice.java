package com.carservice.CarService.vatInvoice;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.invoice.Invoice;
import com.carservice.CarService.printInvoice.PrintInvoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class VatInvoice extends Invoice {
    @SequenceGenerator(
            name = "vat_invoice_sequence",
            sequenceName = "vat_invoice_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vat_invoice_sequence"
    )
    private Long id;
    public VatInvoice(PrintInvoice printInvoice) {
        super(printInvoice);
    }

    @Override
    public byte[] generateInvoice(Commission commission) {
        return printInvoice.generateInvoice(commission);
    }
}

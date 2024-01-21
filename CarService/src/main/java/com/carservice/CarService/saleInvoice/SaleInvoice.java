package com.carservice.CarService.saleInvoice;

import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.invoice.Invoice;
import com.carservice.CarService.printInvoice.PrintInvoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class SaleInvoice extends Invoice {
    @SequenceGenerator(
            name = "sale_invoice_sequence",
            sequenceName = "sale_invoice_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sale_invoice_sequence"
    )
    private Long id;
    public SaleInvoice(PrintInvoice printInvoice) {
        super(printInvoice);
    }

    @Override
    public byte[] generateInvoice(Commission commission) {
        Callable<byte[]> invoiceGenerationCallable = () -> printInvoice.generateInvoice(commission);

        FutureTask<byte[]> futureTask = new FutureTask<>(invoiceGenerationCallable);

        Thread invoiceGenerationThread = new Thread(futureTask);
        invoiceGenerationThread.start();

        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}

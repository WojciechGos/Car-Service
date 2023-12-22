package com.carservice.CarService.payment;

import com.carservice.CarService.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Payment {
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(
            name = "client_id",
            nullable = false
    )
    private Client client;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Payment(
            BigDecimal amount,
            Client client,
            PaymentStatus paymentStatus
    ) {
        this.amount = amount;
        this.client = client;
        this.paymentStatus = paymentStatus;
    }
}

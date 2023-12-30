package com.carservice.CarService.OrderSparePart;

import com.carservice.CarService.sparePart.SparePart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderSparePart {

    @SequenceGenerator(
            name = "order_spare_part_sequence",
            sequenceName = "order_spare_part_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_spare_part_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spare_part_id", nullable = false)
    private SparePart sparePart;

    private Integer quantity;

    public OrderSparePart(SparePart sparePart, Integer quantity) {
        this.sparePart = sparePart;
        this.quantity = quantity;
    }
}

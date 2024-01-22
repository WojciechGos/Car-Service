package com.carservice.CarService.cost;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Cost {
    @SequenceGenerator(
            name = "cost_sequence",
            sequenceName = "cost_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cost_sequence"
    )
    private Long id;
    @NotBlank
    private String name;
    private LocalDateTime createDate;
    @OneToMany
    @JoinColumn(name = "order_spare_part_id")
    private List<OrderSparePart> spareParts;
    private BigDecimal laborPrice;

    public Cost(
            String name,
            LocalDateTime createDate,
            List<OrderSparePart> spareParts,
            BigDecimal laborPrice
    ) {
        this.name = name;
        this.createDate = createDate;
        this.spareParts = spareParts;
        this.laborPrice = laborPrice;
    }

    public Cost(
            String name,
            List<OrderSparePart> spareParts,
            BigDecimal laborPrice
    ) {
        this.name = name;
        this.createDate = LocalDateTime.now();
        this.spareParts = spareParts;
        this.laborPrice = laborPrice;
    }

    public BigDecimal getTotalCost() {
        BigDecimal sparePartsCost = BigDecimal.valueOf(0);

        for (OrderSparePart sparePart : spareParts) {
            sparePartsCost = sparePartsCost.add(sparePart.getSparePart().getPrice());
        }

        return laborPrice.add(sparePartsCost);
    }

}

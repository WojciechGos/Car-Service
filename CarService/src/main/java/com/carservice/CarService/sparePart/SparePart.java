package com.carservice.CarService.sparePart;

import com.carservice.CarService.item.Item;
import com.carservice.CarService.producer.Producer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class SparePart extends Item {
    @SequenceGenerator(
            name = "spare_part_sequence",
            sequenceName = "spare_part_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spare_part_sequence"
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private SparePartState sparePartState;

    public SparePart(
            String name,
            BigDecimal price,
            Integer quantity,
            Producer producer,
            SparePartState sparePartState
    ) {
        super(name, price, quantity, producer);
        this.sparePartState = sparePartState;
    }

}

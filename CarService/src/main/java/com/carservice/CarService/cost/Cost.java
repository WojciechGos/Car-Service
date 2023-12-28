package com.carservice.CarService.cost;

import com.carservice.CarService.sparePart.SparePart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate createDate;
    @ManyToMany
    @JoinTable(
            name = "cost_spare_part",
            joinColumns = @JoinColumn(name = "cost_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_part_id")
    )
    private List<SparePart> spareParts;
    private BigDecimal laborPrice;
    private BigDecimal totalCost;

    public Cost(
            String name,
            LocalDate createDate,
            List<SparePart> spareParts,
            BigDecimal laborPrice,
            BigDecimal totalCost
    ) {
        this.name = name;
        this.createDate = createDate;
        this.spareParts = spareParts;
        this.laborPrice = laborPrice;
        this.totalCost = totalCost;
    }
}

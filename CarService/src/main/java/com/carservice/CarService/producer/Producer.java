package com.carservice.CarService.producer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Producer {
    @SequenceGenerator(
            name = "producer_sequence",
            sequenceName = "producer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "producer_sequence"
    )
    private Long id;
    @NotBlank
    private String name;
    public Producer(String name) {
        this.name = name;
    }
}

package com.carservice.CarService.client;

import com.carservice.CarService.personalData.PersonalData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client extends PersonalData {
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long id;
    public Client(
            Long id,
            String name,
            String surname,
            String email,
            String phoneNumber
    ) {
        super(name, surname, email, phoneNumber);
        this.id = id;
    }
    public Client(
            String name,
            String surname,
            String email,
            String phoneNumber
    ) {
        super(name, surname, email, phoneNumber);
    }
}

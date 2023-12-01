package com.carservice.CarService.personalData;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class PersonalData {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Column(
            unique = true
    )
    private String email;
    private String phoneNumber;
    public PersonalData(
            String name,
            String surname,
            String email,
            String phoneNumber
    ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

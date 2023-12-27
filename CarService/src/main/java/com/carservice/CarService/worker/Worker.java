package com.carservice.CarService.worker;

import com.carservice.CarService.personalData.PersonalData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Worker extends PersonalData implements UserDetails {
    @SequenceGenerator(
            name = "worker_sequence",
            sequenceName = "worker_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "worker_sequence"
    )
    private Long id;
    private BigDecimal salaryPerHour;
    private WorkerRole workerRole;
    @Column(
            nullable = false
    )
    private String password;
    private boolean locked;
    private boolean enabled;

    public Worker(
            String name,
            String surname,
            String email,
            String phoneNumber,
            BigDecimal salaryPerHour,
            WorkerRole workerRole,
            String password
    ) {
        super(name, surname, email, phoneNumber);
        this.salaryPerHour = salaryPerHour;
        this.workerRole = workerRole;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(workerRole.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

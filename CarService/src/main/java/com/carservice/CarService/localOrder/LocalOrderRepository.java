package com.carservice.CarService.localOrder;

import com.carservice.CarService.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalOrderRepository extends JpaRepository<LocalOrder, Long> {
}


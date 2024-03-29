package com.carservice.CarService.localOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalOrderRepository extends JpaRepository<LocalOrder, Long> {
    List<LocalOrder> findByWorkerId(Long id);
}


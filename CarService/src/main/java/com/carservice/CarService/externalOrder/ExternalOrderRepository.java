package com.carservice.CarService.externalOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalOrderRepository extends JpaRepository<ExternalOrder, Long> {
    List<ExternalOrder> findByWorkerId(Long id);
}
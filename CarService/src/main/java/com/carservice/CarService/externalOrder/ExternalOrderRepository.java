package com.carservice.CarService.externalOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalOrderRepository extends JpaRepository<ExternalOrder, Long> {
}
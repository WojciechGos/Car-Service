package com.carservice.CarService.saleInvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
    List<SaleInvoice> findByCommissionId(Long commissionId);
}

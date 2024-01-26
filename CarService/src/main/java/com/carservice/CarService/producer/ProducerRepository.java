package com.carservice.CarService.producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    @Query("SELECT p FROM Producer p WHERE p.name =?1")
    Optional<Producer> findByName(String name);
}

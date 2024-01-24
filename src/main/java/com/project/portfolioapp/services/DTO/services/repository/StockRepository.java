package com.project.portfolioapp.services.DTO.services.repository;

import com.project.portfolioapp.services.DTO.services.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByStockId(String stockId);
}

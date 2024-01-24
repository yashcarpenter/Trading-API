package com.project.portfolioapp.services.DTO.repository;

import com.project.portfolioapp.services.DTO.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByStockId(String stockId);
}


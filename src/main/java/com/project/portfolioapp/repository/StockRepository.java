package com.project.portfolioapp.repository;

import com.project.portfolioapp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByStockId(String stockId);
}


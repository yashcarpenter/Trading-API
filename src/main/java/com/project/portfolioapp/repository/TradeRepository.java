package com.project.portfolioapp.repository;

import com.project.portfolioapp.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByUserAccountId(String userAccountId);
}


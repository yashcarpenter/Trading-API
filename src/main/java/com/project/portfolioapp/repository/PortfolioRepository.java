package com.project.portfolioapp.repository;
import com.project.portfolioapp.model.PortfolioList;
import com.project.portfolioapp.model.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// PortfolioRepository.java
public interface PortfolioRepository extends JpaRepository<PortfolioList, UserStockId> {
    List<PortfolioList> findByUserStockIdUserId(String userId);
}




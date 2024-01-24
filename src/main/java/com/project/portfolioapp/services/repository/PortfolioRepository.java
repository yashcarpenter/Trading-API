package com.project.portfolioapp.services.repository;
import com.project.portfolioapp.services.model.PortfolioList;
import com.project.portfolioapp.services.model.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// PortfolioRepository.java
public interface PortfolioRepository extends JpaRepository<PortfolioList, UserStockId> {
    List<PortfolioList> findByUserStockIdUserId(String userId);
}




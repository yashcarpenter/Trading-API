package com.project.portfolioapp.services.DTO.services.repository;
import com.project.portfolioapp.services.DTO.services.model.PortfolioList;
import com.project.portfolioapp.services.DTO.services.model.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// PortfolioRepository.java
public interface PortfolioRepository extends JpaRepository<PortfolioList, UserStockId> {
    List<PortfolioList> findByUserStockIdUserId(String userId);
}




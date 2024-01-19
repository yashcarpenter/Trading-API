package com.project.portfolioapp.repository;
import com.project.portfolioapp.model.Portfolio;
import com.project.portfolioapp.model.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// PortfolioRepository.java
public interface PortfolioRepository extends JpaRepository<Portfolio, UserStockId> {

//    @Query("SELECT p FROM Portfolio p WHERE p.userId = :userId")
//    List<Portfolio> findByUserId(String userId);
    List<Portfolio> findByUserStockIdUserId(String userId);

}




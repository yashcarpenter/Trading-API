package com.project.portfolioapp.services.DTO.services.model;

import com.project.portfolioapp.services.DTO.services.DTO.PortfolioListDTO;
import lombok.Data;

import java.util.List;

@Data
public class Portfolio {
    private String UserId;
    private List<PortfolioListDTO> holdings;
    private int totalPortfolioHolding;
    private double totalBuyPrice;
    private double totalProfitLoss;
    private double totalPercentageProfitLoss;

    public Portfolio(String userId, List<PortfolioListDTO> holdings, int totalPortfolioHolding, double totalBuyPrice, double totalPercentageProfitLoss, double totalProfitLoss) {
        UserId = userId;
        this.holdings = holdings;
        this.totalPortfolioHolding = totalPortfolioHolding;
        this.totalBuyPrice = totalBuyPrice;
        this.totalPercentageProfitLoss = totalPercentageProfitLoss;
        this.totalProfitLoss = totalProfitLoss;
    }

    public Portfolio() {
    }
}

package com.project.portfolioapp.DTO;

import lombok.Data;

@Data
public class PortfolioInfoDTO {

    private String stockName;
    private String stockId;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private double gainLoss;
    private double totalPortfolioHolding;
    private double totalBuyPrice;
    private double totalPL;
    private double plPercentage;

    public PortfolioInfoDTO(String stockName, String stockId, int quantity, double buyPrice, double currentPrice, double gainLoss, double totalPortfolioHolding, double totalBuyPrice, double totalPL, double plPercentage) {
        this.stockName = stockName;
        this.stockId = stockId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.gainLoss = gainLoss;
        this.totalPortfolioHolding = totalPortfolioHolding;
        this.totalBuyPrice = totalBuyPrice;
        this.totalPL = totalPL;
        this.plPercentage = plPercentage;
    }

    public PortfolioInfoDTO() {
    }

    // Constructors, getters, setters...
}

package com.project.portfolioapp.DTO;

import lombok.Data;

@Data
public class PortfolioListDTO {

    private String stockName;
    private String stockId;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private double gainLoss;

    public PortfolioListDTO(String stockName, String stockId, int quantity, double buyPrice, double currentPrice, double gainLoss) {
        this.stockName = stockName;
        this.stockId = stockId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.gainLoss = gainLoss;
    }

    public PortfolioListDTO() {
    }

    // Constructors, getters, setters...
}

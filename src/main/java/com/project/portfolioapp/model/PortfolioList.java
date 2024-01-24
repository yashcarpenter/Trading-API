package com.project.portfolioapp.model;

import jakarta.persistence.*;
import lombok.Data;

// Portfolio.java
@Entity
@Data
public class PortfolioList {
    @EmbeddedId
    private UserStockId userStockId;

    @Column
    private String stockName;
    @Column
    private int quantity;
    @Column
    private double buyPrice;

    public PortfolioList(UserStockId userStockId, int quantity, double buyPrice, String stockName) {
        this.userStockId = userStockId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.stockName = stockName;
    }

    public PortfolioList() {
    }
}


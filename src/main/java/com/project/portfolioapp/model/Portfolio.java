package com.project.portfolioapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// Portfolio.java
@Entity
@Data
public class Portfolio {
    @EmbeddedId
    private UserStockId userStockId;




//    @ManyToOne
//    @JoinColumn(name = "stockId", referencedColumnName = "stock_Id", insertable = false, updatable = false)
//    @OnDelete(action= OnDeleteAction.CASCADE)
//    private Stock stock;

    @Column
    private String stockName;
    @Column
    private int quantity;
    @Column
    private double buyPrice;

    public Portfolio(UserStockId userStockId, int quantity, double buyPrice, String stockName) {
        this.userStockId = userStockId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.stockName = stockName;
    }

    public Portfolio() {
    }
}


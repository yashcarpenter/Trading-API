package com.project.portfolioapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Data
public class Stock {
    @Id
    @Column(name = "stock_Id")
    private String stockId;

    @Column
    private String stockName;
    @Column
    private double openPrice;
    @Column
    private double closePrice;
    @Column
    private double highPrice;
    @Column
    private double lowPrice;
    @Column
    private double lastPrice;

    public Stock(
            String stockId,
            String stockName,
            double openPrice,
            double closePrice,
            double highPrice,
            double lowPrice,
            double lastPrice) {

        this.stockId = stockId;
        this.stockName = stockName;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.lastPrice = lastPrice;
    }

    public Stock() {
    }
}

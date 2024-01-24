package com.project.portfolioapp.services.DTO.services.model;

import jakarta.persistence.*;
import lombok.Data;

// Trade.java
@Entity
@Data
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userAccountId;
    @Column
    private String tradeType;
    @Column
    private int quantity;
    @Column
    private String stockId;

    // No getters and setters because of lombok

    public Trade(Long id, String userAccountId, String tradeType, int quantity, String stockId) {
        this.userAccountId = userAccountId;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.stockId = stockId;
    }

    public Trade() {
    }
}


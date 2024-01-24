package com.project.portfolioapp.services.DTO.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserStockId implements Serializable {

    private String userId;
    private String stockId;

    public UserStockId() {}

    public UserStockId(String userId, String stockId) {
        this.userId = userId;
        this.stockId = stockId;
    }

}

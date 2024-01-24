package com.project.portfolioapp.services.controller;

import com.project.portfolioapp.services.model.Stock;
import com.project.portfolioapp.services.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// StockController.java
@RestController
@RequestMapping("/api")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getStockDetails(){
        List<Stock> stock = stockService.getAll();
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<Stock> getStockById(@PathVariable String stockId) {
        Stock stock = stockService.findByStockId(stockId);
        if (stock != null) {
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


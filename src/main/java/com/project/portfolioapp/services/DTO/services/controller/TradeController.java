package com.project.portfolioapp.services.DTO.services.controller;

import com.project.portfolioapp.services.DTO.services.model.Trade;
import com.project.portfolioapp.services.DTO.services.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<Trade> trade(@RequestBody Trade trade) {
        Trade savedTrade = tradeService.saveTrade(trade);
        return new ResponseEntity<>(savedTrade, HttpStatus.OK);
    }

    @GetMapping("/{userAccountId}")
    public ResponseEntity<List<Trade>> getTrades(@PathVariable String userAccountId) {
        List<Trade> trades = tradeService.getTradesByUserAccountId(userAccountId);
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }
}


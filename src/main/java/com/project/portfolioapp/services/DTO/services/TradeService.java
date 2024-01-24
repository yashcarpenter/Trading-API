package com.project.portfolioapp.services.DTO.services;

import com.project.portfolioapp.services.DTO.model.Stock;
import com.project.portfolioapp.services.DTO.model.Trade;
import com.project.portfolioapp.services.DTO.repository.StockRepository;
import com.project.portfolioapp.services.DTO.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService{
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PortfolioService portfolioService;

    public Trade saveTrade(Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        portfolioService.updatePortfolio(savedTrade);
        return savedTrade;
    }

    public String fetchStockName(String stockId) {
        Optional<Stock> stockOptional = stockRepository.findByStockId(stockId);
        return stockOptional.map(Stock::getStockName)
                .orElseThrow(() -> new StockNotFoundException("Stock not found for stockId: " + stockId));
    }

    public double fetchClosePrice(String stockId) {
        Optional<Stock> stockOptional = stockRepository.findByStockId(stockId);
        return stockOptional.map(Stock::getClosePrice)
                .orElseThrow(() -> new StockNotFoundException("This Stock does not exist hence unable to set Buy Price"));
    }

    public static class StockNotFoundException extends RuntimeException {
        public StockNotFoundException(String message) {
            super(message);
        }
    }

    public List<Trade> getTradesByUserAccountId(String userAccountId) {
        return tradeRepository.findByUserAccountId(userAccountId);
    }
}

package com.project.portfolioapp.services;

import com.project.portfolioapp.DTO.PortfolioInfoDTO;
import com.project.portfolioapp.model.*;
import com.project.portfolioapp.repository.PortfolioRepository;
import com.project.portfolioapp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TradeService tradeService;

    public List<Portfolio> getAll() {
        return portfolioRepository.findAll();
    }

    public void updatePortfolio(Trade trade) {
        String userAccountId = trade.getUserAccountId();
        String stockId = trade.getStockId();

        String tradeType = trade.getTradeType();
        String stockName = tradeService.fetchStockName(trade.getStockId());
        int quantity = trade.getQuantity();
        double buyPrice = tradeService.fetchClosePrice(trade.getStockId());
        UserStockId userStockId = new UserStockId(userAccountId, stockId);

        Optional<Portfolio> existingPortfolio = portfolioRepository.findById(userStockId);

        if (existingPortfolio.isPresent()) {
            Portfolio portfolio = existingPortfolio.get();
            updatePortfolioEntry(portfolio, quantity, buyPrice, tradeType);
            portfolioRepository.save(portfolio);
        } else {
            Portfolio newPortfolioEntry = new Portfolio(userStockId, quantity, buyPrice, stockName);
            portfolioRepository.save(newPortfolioEntry);
        }
    }

    private static ResponseEntity<String> updatePortfolioEntry(Portfolio portfolio, int quantity, double tradePrice, String tradeType) {
        if ("Buy".equalsIgnoreCase(tradeType)) {
            portfolio.setQuantity(portfolio.getQuantity() + quantity);
            portfolio.setBuyPrice((portfolio.getBuyPrice() * portfolio.getQuantity() + tradePrice * quantity) /
                    (portfolio.getQuantity() + quantity));
            return new ResponseEntity<>("Portfolio Updated successfully", HttpStatus.OK);
        } else if ("Sell".equalsIgnoreCase(tradeType)) {
            int netQuantity = portfolio.getQuantity() - quantity;
            if(netQuantity>0){
                portfolio.setQuantity(netQuantity);
                return new ResponseEntity<>("Portfolio Updated successfully", HttpStatus.OK);
            } else if(netQuantity<0){
                return new ResponseEntity<>("Insufficient Quantity to Sell", HttpStatus.NOT_ACCEPTABLE);
            } else {
                // Delete the portfolio entry if netQuantity is zero
//                portfolioRepository.deleteByUserAccountIdAndStockId(portfolio.getUserAccountId(), portfolio.getStockId());
                return new ResponseEntity<>("Portfolio Entry Deleted successfully", HttpStatus.OK);
            }
        } else{
            return new ResponseEntity<>("Invalid Trade Type", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public List<PortfolioInfoDTO> getPortfolioInfoByUserId(String userId) {
        List<Portfolio> userPortfolios = portfolioRepository.findByUserStockIdUserId(userId);

        return userPortfolios.stream()
                .map(this::mapToPortfolioInfoDTO)
                .collect(Collectors.toList());
    }

    private PortfolioInfoDTO mapToPortfolioInfoDTO(Portfolio portfolio) {
        PortfolioInfoDTO portfolioInfoDTO = new PortfolioInfoDTO();
        portfolioInfoDTO.setStockName(portfolio.getStockName());
        portfolioInfoDTO.setStockId(portfolio.getUserStockId().getStockId());
        portfolioInfoDTO.setQuantity(portfolio.getQuantity());
        portfolioInfoDTO.setBuyPrice(portfolio.getBuyPrice());

        // Fetch close price from Stock table
        Optional<Stock> stockOptional = stockRepository.findByStockId(portfolio.getUserStockId().getStockId());
        double closePrice = stockOptional.map(Stock::getClosePrice).orElse(0.0);
        portfolioInfoDTO.setCurrentPrice(closePrice);

        // Calculate gain/loss
        double gainLoss = (closePrice - portfolio.getBuyPrice()) * portfolio.getQuantity();
        portfolioInfoDTO.setGainLoss(gainLoss);

        return portfolioInfoDTO;
    }
//    public List<Portfolio> getPortfolioByUser(String userAccountId) {
//        return portfolioRepository.findByUserAccountId(userAccountId);
//    }
}


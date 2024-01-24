package com.project.portfolioapp.services.DTO.services.services;

import com.project.portfolioapp.services.DTO.services.DTO.PortfolioListDTO;
import com.project.portfolioapp.services.DTO.services.model.*;
import com.project.portfolioapp.services.DTO.services.repository.PortfolioRepository;
import com.project.portfolioapp.services.DTO.services.repository.StockRepository;
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

//    @Autowired
    private PortfolioListDTO portfolioListDTO;

    public List<PortfolioList> getAll() {
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

        Optional<PortfolioList> existingPortfolio = portfolioRepository.findById(userStockId);

        if (existingPortfolio.isPresent()) {
            PortfolioList portfolio = existingPortfolio.get();
            updatePortfolioEntry(portfolio, quantity, buyPrice, tradeType);
            portfolioRepository.save(portfolio);
        } else {
            PortfolioList newPortfolioEntry = new PortfolioList(userStockId, quantity, buyPrice, stockName);
            portfolioRepository.save(newPortfolioEntry);
        }
    }

    private static ResponseEntity<String> updatePortfolioEntry(PortfolioList portfolio, int quantity, double tradePrice, String tradeType) {
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
    public List<PortfolioListDTO> getPortfolioInfoByUserId(String userId) {
        List<PortfolioList> userPortfolios = portfolioRepository.findByUserStockIdUserId(userId);

        return userPortfolios.stream()
                .map(this::mapToPortfolioInfoDTO)
                .collect(Collectors.toList());
    }

    private PortfolioListDTO mapToPortfolioInfoDTO(PortfolioList portfolio) {
        PortfolioListDTO portfolioListDTO = new PortfolioListDTO();
        portfolioListDTO.setStockName(portfolio.getStockName());
        portfolioListDTO.setStockId(portfolio.getUserStockId().getStockId());
        portfolioListDTO.setQuantity(portfolio.getQuantity());
        portfolioListDTO.setBuyPrice(portfolio.getBuyPrice());

        // Fetch close price from Stock table
        Optional<Stock> stockOptional = stockRepository.findByStockId(portfolio.getUserStockId().getStockId());
        double closePrice = stockOptional.map(Stock::getClosePrice).orElse(0.0);
        portfolioListDTO.setCurrentPrice(closePrice);

        // Calculate gain/loss
        double gainLoss = (closePrice - portfolio.getBuyPrice()) * portfolio.getQuantity();
        portfolioListDTO.setGainLoss(gainLoss);

        return portfolioListDTO;
    }

    public int calculateTotalHoldings(List<PortfolioListDTO> portfolioList) {
        // Using Java Stream API to sum the quantities
        int totalHoldings = portfolioList.stream()
                .mapToInt(PortfolioListDTO::getQuantity)
                .sum();

        return totalHoldings;
    }

    public double calculateTotalBuyPrice(List<PortfolioListDTO> portfolioList) {
        // Using Java Stream API to calculate the total buy price
        double totalBuyPrice = portfolioList.stream()
                .mapToDouble(PortfolioListDTO::getBuyPrice)
                .sum();

        return totalBuyPrice;
    }

    public double calculateTotalProfitLoss(List<PortfolioListDTO> portfolioList) {
        // Using Java Stream API to calculate the total gain/loss
        return portfolioList.stream()
                .mapToDouble(PortfolioListDTO::getGainLoss)
                .sum();
    }

    public Portfolio getPortfolio(String userId) {
        List<PortfolioListDTO> portfolioListDTOList;
        portfolioListDTOList = getPortfolioInfoByUserId(userId);
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(userId);
        portfolio.setHoldings(portfolioListDTOList);
        portfolio.setTotalPortfolioHolding(calculateTotalHoldings(portfolioListDTOList));
        portfolio.setTotalBuyPrice(calculateTotalBuyPrice(portfolioListDTOList));
        portfolio.setTotalProfitLoss(calculateTotalProfitLoss(portfolioListDTOList));
        double totalProfitLossPercentage = (calculateTotalProfitLoss(portfolioListDTOList)/calculateTotalBuyPrice(portfolioListDTOList))*100;
        portfolio.setTotalPercentageProfitLoss(totalProfitLossPercentage);
        return portfolio;
    }


}


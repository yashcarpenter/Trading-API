package com.project.portfolioapp.ServiceTest;

import com.project.portfolioapp.model.Trade;
import com.project.portfolioapp.repository.StockRepository;
import com.project.portfolioapp.repository.TradeRepository;
import com.project.portfolioapp.services.PortfolioService;
import com.project.portfolioapp.services.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TradeServiceTests {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private PortfolioService portfolioService;

    @Test
    public void testSaveTrade() {
        // Mocking data for testing
        Trade trade = new Trade(1L, "user1", "Buy", 10, "stock1");

        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        Trade savedTrade = tradeService.saveTrade(trade);

        assertNotNull(savedTrade);
        assertEquals("user1", savedTrade.getUserAccountId());
        // Add more assertions based on your specific requirements
    }
    // Add more test cases for other methods in TradeService
}

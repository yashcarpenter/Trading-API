package com.project.portfolioapp.ControllerTest;

import com.project.portfolioapp.controller.TradeController;
import com.project.portfolioapp.model.Trade;
import com.project.portfolioapp.services.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeControllerTests {

    @InjectMocks
    private TradeController tradeController;

    @Mock
    private TradeService tradeService;

    @Test
    public void testTrade() {
        // Mocking data for testing
        Trade mockTrade = new Trade(1L, "user1", "Buy", 10, "stock1");

        when(tradeService.saveTrade(any(Trade.class))).thenReturn(mockTrade);

        ResponseEntity<Trade> response = tradeController.trade(mockTrade);

        assertEquals(mockTrade, response.getBody());
        // Add more assertions based on your specific requirements
    }

    @Test
    public void testGetTrades() {
        // Mocking data for testing
        String userAccountId = "user1";
        List<Trade> mockTrades = Arrays.asList(
                new Trade(1L, userAccountId, "Buy", 10, "stock1"),
                new Trade(2L, userAccountId, "Sell", 5, "stock2")
        );

        when(tradeService.getTradesByUserAccountId(userAccountId)).thenReturn(mockTrades);

        ResponseEntity<List<Trade>> response = tradeController.getTrades(userAccountId);

        assertEquals(2, response.getBody().size());
        // Add more assertions based on your specific requirements
    }

    // Add more test cases for other methods in TradeController
}


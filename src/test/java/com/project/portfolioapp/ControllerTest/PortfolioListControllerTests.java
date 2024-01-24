package com.project.portfolioapp.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.portfolioapp.controller.PortfolioController;
import com.project.portfolioapp.model.PortfolioList;
import com.project.portfolioapp.model.UserStockId;
import com.project.portfolioapp.services.PortfolioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PortfolioListControllerTests {

    @InjectMocks
    private PortfolioController portfolioController;

    @Mock
    private PortfolioService portfolioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetPortfolioDetails() {
        // Mocking data for testing
        List<PortfolioList> mockPortfolios = Arrays.asList(
                new PortfolioList(new UserStockId("user1", "stock1"), 10, 100.0, "Stock1"),
                new PortfolioList(new UserStockId("user1", "stock2"), 5, 50.0, "Stock2")
        );

        when(portfolioService.getAll()).thenReturn(mockPortfolios);

        ResponseEntity<List<PortfolioList>> response = portfolioController.getPortfolioDetails();

        assertEquals(2, response.getBody().size());
        // Add more assertions based on your specific requirements
    }

    // Add more test cases for other methods in PortfolioController
}


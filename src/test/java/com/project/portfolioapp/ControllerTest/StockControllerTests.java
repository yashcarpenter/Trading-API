package com.project.portfolioapp.ControllerTest;

import com.project.portfolioapp.controller.StockController;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.services.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockControllerTests {

    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    @Test
    public void testGetStockDetails() {
        // Mocking data for testing
        List<Stock> mockStocks = Arrays.asList(
                new Stock("stock1", "Stock1", 100.0, 110.0, 120.0, 90.0, 105.0),
                new Stock("stock2", "Stock2", 50.0, 55.0, 60.0, 45.0, 52.5)
        );

        when(stockService.getAll()).thenReturn(mockStocks);

        ResponseEntity<List<Stock>> response = stockController.getStockDetails();

        assertEquals(2, response.getBody().size());
        // Add more assertions based on your specific requirements
    }

    @Test
    public void testGetStockById() {
        // Mocking data for testing
        String stockId = "stock1";
        Stock mockStock = new Stock(stockId, "Stock1", 100.0, 110.0, 120.0, 90.0, 105.0);

        when(stockService.findByStockId(stockId)).thenReturn(mockStock);

        ResponseEntity<Stock> response = stockController.getStockById(stockId);

        assertEquals(mockStock, response.getBody());
    }
}


package com.project.portfolioapp.ServiceTest;

import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.repository.StockRepository;
import com.project.portfolioapp.services.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StockServiceTests {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testGetAllStocks() {
        // Mocking data for testing
        List<Stock> mockStocks = Arrays.asList(
                new Stock("stock1", "Stock1", 100.0, 110.0, 120.0, 90.0, 105.0),
                new Stock("stock2", "Stock2", 50.0, 55.0, 60.0, 45.0, 52.5)
        );

        when(stockRepository.findAll()).thenReturn(mockStocks);

        List<Stock> stocks = stockService.getAll();

        assertEquals(2, stocks.size());
    }
}

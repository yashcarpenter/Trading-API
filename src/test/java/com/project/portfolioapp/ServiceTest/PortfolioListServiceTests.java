package com.project.portfolioapp.ServiceTest;

import com.project.portfolioapp.model.PortfolioList;
import com.project.portfolioapp.model.UserStockId;
import com.project.portfolioapp.repository.PortfolioRepository;
import com.project.portfolioapp.repository.StockRepository;
import com.project.portfolioapp.services.PortfolioService;
import com.project.portfolioapp.services.TradeService;
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
public class PortfolioListServiceTests {

    @InjectMocks
    private PortfolioService portfolioService;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private TradeService tradeService;

    @Test
    public void testGetAllPortfolios() {
        // Mocking data for testing
        List<PortfolioList> mockPortfolios = Arrays.asList(
                new PortfolioList(new UserStockId("user1", "stock1"), 10, 100.0, "Stock1"),
                new PortfolioList(new UserStockId("user1", "stock2"), 5, 50.0, "Stock2")
        );

        when(portfolioRepository.findAll()).thenReturn(mockPortfolios);

        List<PortfolioList> portfolios = portfolioService.getAll();

        assertEquals(2, portfolios.size());
    }
}

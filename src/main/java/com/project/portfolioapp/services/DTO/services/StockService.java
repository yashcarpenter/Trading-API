package com.project.portfolioapp.services.DTO.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.project.portfolioapp.services.DTO.model.Stock;
import com.project.portfolioapp.services.DTO.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.parseDouble;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }
    public Stock findByStockId(String stockId) {
        Optional<Stock> stockOptional = stockRepository.findByStockId(stockId);
        return stockOptional.orElse(null);
    }


    public void ParseAndUpdateStock(InputStream fileinputstream){
        try(CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(fileinputstream)).build()) {
            csvReader.skip(1);
            List<String[]> rows = csvReader.readAll();
            List<Stock> stockList = new ArrayList<>();
            for(String[] columns: rows) {
                String stockID = columns[12];
                String stockName = columns[0];
                double openPrice = parseDouble(columns[2]);
                double closePrice = parseDouble(columns[5]);
                double highPrice = parseDouble(columns[3]);
                double lowPrice = parseDouble(columns[4]);
                double lastPrice = parseDouble(columns[6]);
                Stock stock = new Stock(stockID, stockName, openPrice, closePrice, highPrice, lowPrice, lastPrice);
                stockList.add(stock);
            }
            stockRepository.saveAll(stockList);
        } catch(IOException e){
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}





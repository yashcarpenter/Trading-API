package com.project.portfolioapp.services.DTO.controller;

import com.project.portfolioapp.services.DTO.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UpdateStocksController{

    @Autowired
    private StockService stockService;

    @PutMapping("/upload-csv")
    public ResponseEntity<String> uploadCsv(@RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file.");
        }
        stockService.ParseAndUpdateStock(file.getInputStream());
        return ResponseEntity.ok("stocks updated");
    }

}



package com.project.portfolioapp.controller;

import com.project.portfolioapp.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

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



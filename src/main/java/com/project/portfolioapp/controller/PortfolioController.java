package com.project.portfolioapp.controller;

import com.project.portfolioapp.DTO.PortfolioInfoDTO;
import com.project.portfolioapp.model.Portfolio;
import com.project.portfolioapp.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("portfolio")
    public ResponseEntity<List<Portfolio>> getPortfolioDetails(){
        List<Portfolio> portfolio = portfolioService.getAll();
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @GetMapping("/portfolio/{userId}")
    public List<PortfolioInfoDTO> getPortfolioInfoByUserId(@PathVariable String userId) {
        return portfolioService.getPortfolioInfoByUserId(userId);
    }
//    public ResponseEntity<List<Portfolio>> getPortfolioByUser(@PathVariable String userAccountId) {
//        List<Portfolio> portfolio = portfolioService.getPortfoliosByUserId(userAccountId);;
//        return new ResponseEntity<>(portfolio, HttpStatus.OK);
//    }
}


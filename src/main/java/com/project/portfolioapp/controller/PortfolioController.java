package com.project.portfolioapp.controller;

import com.project.portfolioapp.DTO.PortfolioListDTO;
import com.project.portfolioapp.model.PortfolioList;
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

    @GetMapping("/portfolio")
    public ResponseEntity<List<PortfolioList>> getPortfolioDetails(){
        List<PortfolioList> portfolio = portfolioService.getAll();
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @GetMapping("/portfolio/{userId}")
    public Portfolio getPortfolioInfoByUserId(@PathVariable String userId) {
        List<PortfolioListDTO> portfolioListDTOList = new ArrayList<>();
        return portfolioService.getPortfolio(userId);
    }


}
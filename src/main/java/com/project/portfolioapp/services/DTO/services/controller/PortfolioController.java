package com.project.portfolioapp.services.DTO.services.controller;

import com.project.portfolioapp.services.DTO.services.DTO.PortfolioListDTO;
import com.project.portfolioapp.services.DTO.services.model.Portfolio;
import com.project.portfolioapp.services.DTO.services.model.PortfolioList;
import com.project.portfolioapp.services.DTO.services.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
package com.calc.controller;

import com.calc.model.CreditCalculator;
import com.calc.service.CreditCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestCalculatorController {
    private final CreditCalculatorService creditCalculatorService;

    @Autowired
    public RestCalculatorController(CreditCalculatorService creditCalculatorService) {
        this.creditCalculatorService = creditCalculatorService;
    }

    @GetMapping
    public ResponseEntity<List<CreditCalculator>> getJSONForCreditCalc(@RequestParam(required = false, defaultValue = "300000") BigDecimal sumOfCredit,
                                                                       @RequestParam(required = false, defaultValue = "12") Integer termsOfCredit,
                                                                       @RequestParam(required = false, defaultValue = "12.9") BigDecimal percentRate) {
        return new ResponseEntity<>(creditCalculatorService.creditCalculatorList(sumOfCredit, termsOfCredit, percentRate), HttpStatus.OK);
    }
}
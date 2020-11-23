package com.calc.controller;

import com.calc.service.CreditCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/")
public class CalculateController {
    private final CreditCalculatorService creditCalculatorService;

    @Autowired
    public CalculateController(CreditCalculatorService creditCalculatorService) {
        this.creditCalculatorService = creditCalculatorService;
    }

    @GetMapping
    public ModelAndView calculation(@RequestParam(required = false, defaultValue = "0") BigDecimal sumOfCredit,
                                    @RequestParam(required = false, defaultValue = "1") Integer termsOfCredit,
                                    @RequestParam(required = false, defaultValue = "1") BigDecimal percentRate) {
        return creditCalculatorService.creditCalculator(sumOfCredit, termsOfCredit, percentRate);
    }
}
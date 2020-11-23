package com.calc.service;

import com.calc.model.CreditCalculator;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCalculatorService {

    public ModelAndView creditCalculator(BigDecimal sumOfCredit, Integer termsOfCredit, BigDecimal percentRate) {
        List<CreditCalculator> creditCalculatorList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("index");
        mainCode(sumOfCredit, termsOfCredit, percentRate, creditCalculatorList);
        modelAndView.addObject("table", creditCalculatorList);
        return modelAndView;
    }

    public List<CreditCalculator> creditCalculatorList(BigDecimal sumOfCredit, Integer termsOfCredit, BigDecimal percentRate) {
        List<CreditCalculator> creditCalculatorList = new ArrayList<>();
        mainCode(sumOfCredit, termsOfCredit, percentRate, creditCalculatorList);
        return creditCalculatorList;
    }

    private void mainCode(BigDecimal sumOfCredit, Integer termsOfCredit, BigDecimal percentRate, List<CreditCalculator> creditCalculatorList) {
        if (sumOfCredit.doubleValue() >= 100000 && sumOfCredit.doubleValue() <= 5000000
                && termsOfCredit >= 12 && termsOfCredit <= 60
                && percentRate.doubleValue() >= 12.9 && percentRate.doubleValue() <= 23.9) {
            BigDecimal percentMonth = percentRate
                    .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
            BigDecimal monthlyPaymentWithPercents = sumOfCredit
                    .multiply(percentMonth
                            .add(percentMonth
                                    .divide((BigDecimal.valueOf(1)
                                            .add(percentMonth))
                                            .pow(termsOfCredit)
                                            .subtract(BigDecimal.valueOf(1)), 10, RoundingMode.HALF_UP)))
                    .setScale(2, RoundingMode.HALF_UP);
            CreditCalculator baseCreditCalculator = new CreditCalculator(
                    1L,
                    LocalDate.now(),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    sumOfCredit,
                    BigDecimal.valueOf(0));
            creditCalculatorList.add(baseCreditCalculator);
            for (int i = 0; i < termsOfCredit; i++) {
                CreditCalculator creditCalculator = new CreditCalculator();
                creditCalculator.setId(baseCreditCalculator.getId() + i + 1);
                creditCalculator.setLocalDate(LocalDate.now().plusMonths(i + 1L));
                BigDecimal monthPayForPercent = sumOfCredit.multiply(percentMonth).setScale(2, RoundingMode.HALF_UP);
                creditCalculator.setPercentPayment(monthPayForPercent);
                BigDecimal bodyOfCredit = monthlyPaymentWithPercents.subtract(monthPayForPercent).setScale(2, RoundingMode.HALF_UP);
                creditCalculator.setBasePaymentCredit(bodyOfCredit);
                sumOfCredit = sumOfCredit.subtract(bodyOfCredit).setScale(2, RoundingMode.HALF_UP);
                creditCalculator.setFullMountOfPayment(monthPayForPercent.add(bodyOfCredit));
                creditCalculator.setBaseCredit(sumOfCredit);
                if (sumOfCredit.doubleValue() < 10) {
                    creditCalculator.setBasePaymentCredit(bodyOfCredit.add(sumOfCredit));
                    creditCalculator.setBaseCredit(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP));
                }
                creditCalculatorList.add(creditCalculator);
            }
        }
    }
}
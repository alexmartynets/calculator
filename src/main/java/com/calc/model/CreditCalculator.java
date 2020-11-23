package com.calc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CreditCalculator {
    private Long id;
    private LocalDate localDate;
    private BigDecimal basePaymentCredit;
    private BigDecimal percentPayment;
    private BigDecimal baseCredit;
    private BigDecimal fullMountOfPayment;

    public CreditCalculator() {
    }

    public CreditCalculator(Long id, LocalDate localDate, BigDecimal basePaymentCredit, BigDecimal percentPayment, BigDecimal baseCredit, BigDecimal fullMountOfPayment) {
        this.id = id;
        this.localDate = localDate;
        this.basePaymentCredit = basePaymentCredit;
        this.percentPayment = percentPayment;
        this.baseCredit = baseCredit;
        this.fullMountOfPayment = fullMountOfPayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setBasePaymentCredit(BigDecimal basePaymentCredit) {
        this.basePaymentCredit = basePaymentCredit;
    }

    public void setPercentPayment(BigDecimal percentPayment) {
        this.percentPayment = percentPayment;
    }

    public void setBaseCredit(BigDecimal baseCredit) {
        this.baseCredit = baseCredit;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public BigDecimal getBasePaymentCredit() {
        return basePaymentCredit;
    }

    public BigDecimal getPercentPayment() {
        return percentPayment;
    }

    public BigDecimal getBaseCredit() {
        return baseCredit;
    }

    public BigDecimal getFullMountOfPayment() {
        return fullMountOfPayment;
    }

    public void setFullMountOfPayment(BigDecimal fullMountOfPayment) {
        this.fullMountOfPayment = fullMountOfPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCalculator that = (CreditCalculator) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(localDate, that.localDate) &&
                Objects.equals(basePaymentCredit, that.basePaymentCredit) &&
                Objects.equals(percentPayment, that.percentPayment) &&
                Objects.equals(baseCredit, that.baseCredit) &&
                Objects.equals(fullMountOfPayment, that.fullMountOfPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, localDate, basePaymentCredit, percentPayment, baseCredit, fullMountOfPayment);
    }

    @Override
    public String toString() {
        return "CreditCalculator{" +
                "id=" + id +
                ", localDate=" + localDate +
                ", basePaymentCredit=" + basePaymentCredit +
                ", percentPayment=" + percentPayment +
                ", baseCredit=" + baseCredit +
                ", fullMountOfPayment=" + fullMountOfPayment +
                '}';
    }
}
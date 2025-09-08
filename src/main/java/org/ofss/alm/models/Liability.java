package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Liability {
    private Long liabilityId;
    private Long customerId; // FK -> Customer
    private String type; // Deposit, Borrowing, etc.
    private String currencyCode; // FK -> Currency
    private BigDecimal amount;
    private LocalDate maturityDate;
    private BigDecimal interestRate;
    private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Liability(Long liabilityId, Long customerId, String type, String currencyCode,
                     BigDecimal amount, LocalDate maturityDate, BigDecimal interestRate, LocalDate createdDate) {
        this.liabilityId = liabilityId;
        this.customerId = customerId;
        this.type = type;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.maturityDate = maturityDate;
        this.interestRate = interestRate;
        this.createdDate = createdDate;
    }

    // Getters & Setters
    public Long getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(Long liabilityId) {
        this.liabilityId = liabilityId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
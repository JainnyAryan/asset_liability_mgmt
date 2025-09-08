package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Asset {
    private Long assetId;
    private Long customerId; // FK -> Customer
    private String type; // Loan, Investment, Bond, etc.
    private String currencyCode; // FK -> Currency
    private BigDecimal amount;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private BigDecimal interestRate;

    public Asset(Long assetId, Long customerId, String type, String currencyCode,
                 BigDecimal amount, LocalDate maturityDate, BigDecimal interestRate, LocalDate createdDate) {
        this.assetId = assetId;
        this.customerId = customerId;
        this.type = type;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.maturityDate = maturityDate;
        this.interestRate = interestRate;
        this.createdDate = createdDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    // Getters & Setters
    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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
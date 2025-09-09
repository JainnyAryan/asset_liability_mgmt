package org.ofss.alm.models;

import org.ofss.alm.enums.AssetType;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class Asset {

    private static Duration payoutDurationOffset = Duration.ofDays(365);
    private Long assetId;
    private Long customerId; // FK -> Customer
    private AssetType type; // Loan, Bond, etc.
    private String currencyCode; // FK -> Currency
    private BigDecimal amount;
    private LocalDate payoutDate;
    private BigDecimal interestRate;
    private Loan loan;

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

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
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

    public LocalDate getPayoutDate() {
        return payoutDate;
    }

    public void setPayoutDate(LocalDate payoutDate) {
        this.payoutDate = payoutDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
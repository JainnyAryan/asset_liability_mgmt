package org.ofss.alm.models;

import org.ofss.alm.enums.AssetType;
import org.ofss.alm.enums.CurrencyCode;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Asset {

    public static Duration payoutDurationOffsetDays = Duration.ofDays(365);
    private UUID id;
    private Customer customer; // FK -> Customer
    private AssetType type; // Loan, Bond, etc.
    private CurrencyCode currencyCode; // FK -> Currency
    private BigDecimal amount;
    private LocalDate payoutDate;
    private BigDecimal interestRate;
    private Loan loan;
    private LocalDateTime createdAt;



    public Asset() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Asset(Customer customer, AssetType type, CurrencyCode currencyCode, BigDecimal amount, LocalDate payoutDate, BigDecimal interestRate, Loan loan, LocalDateTime createdAt) {
        this.customer = customer;
        this.type = type;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.payoutDate = payoutDate;
        this.interestRate = interestRate;
        this.loan = loan;
        this.createdAt = createdAt;
    }

    public Asset(UUID id, Customer customer, AssetType type, CurrencyCode currencyCode, BigDecimal amount, LocalDate payoutDate, BigDecimal interestRate, Loan loan, LocalDateTime createdAt) {
        this.id = id;
        this.customer = customer;
        this.type = type;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.payoutDate = payoutDate;
        this.interestRate = interestRate;
        this.loan = loan;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
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

    public static Duration getPayoutDurationOffsetDays() {
        return payoutDurationOffsetDays;
    }

    public static void setPayoutDurationOffsetDays(Duration payoutDurationOffsetDays) {
        Asset.payoutDurationOffsetDays = payoutDurationOffsetDays;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
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
    private BigDecimal interestRate;

    // Getters & Setters
    // ...
}
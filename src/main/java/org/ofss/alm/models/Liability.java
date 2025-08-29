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

    // Getters & Setters
    // ...
}
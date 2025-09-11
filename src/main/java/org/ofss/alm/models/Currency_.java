package org.ofss.alm.models;

import org.ofss.alm.enums.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Currency_ {
    private CurrencyCode code; // e.g., "USD", "INR"
    private String name; // e.g., "US Dollar"
    private BigDecimal fxRateToBase;
    private LocalDate effectiveDate;

    // Getters & Setters
    // ...
}
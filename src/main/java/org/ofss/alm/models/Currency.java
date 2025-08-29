package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Currency {
    private String code; // e.g., "USD", "INR"
    private String name; // e.g., "US Dollar"
    private BigDecimal fxRateToBase;
    private LocalDate effectiveDate;

    // Getters & Setters
    // ...
}
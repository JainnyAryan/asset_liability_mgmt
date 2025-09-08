package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Currency {
    private String code; // e.g., "USD", "INR"
    private String name; // e.g., "US Dollar"
    private BigDecimal fxRateToBase;
    private LocalDate effectiveDate;

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getFxRateToBase() {
        return fxRateToBase;
    }

    public void setFxRateToBase(BigDecimal fxRateToBase) {
        this.fxRateToBase = fxRateToBase;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    // ...
}
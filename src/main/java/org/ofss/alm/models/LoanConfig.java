package org.ofss.alm.models;

import java.time.LocalDate;
import java.util.Map;

public class LoanConfig {
    private int id;
    private String loanType;
    private Map<String, Double> weights;       // factor weights
    private Map<String, Integer> thresholds;   // tier thresholds
    private LocalDate effectiveDate;
}
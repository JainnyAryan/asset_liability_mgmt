package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanDecision_ {
    private int id;
    private Customer customer;
    private int applicationId;
    private String riskTier;
    private BigDecimal maxLoanAmount;
    private String decision; // Approve, Reject, Conditional
    private LocalDate decisionDate;
}
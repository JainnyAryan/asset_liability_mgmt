package org.ofss.alm.models;

import org.ofss.alm.enums.RiskTier;

import java.util.UUID;

public class LoanActivityRecord {
    private UUID id;
    private Loan loan;
    private double riskScore;
    private RiskTier riskTier;
    private boolean isLoanRisky;
    private boolean isApproved;

    public LoanActivityRecord() {
        this.id = UUID.randomUUID();
        this.isApproved = false;
    }

    public LoanActivityRecord(Loan loan, double riskScore, RiskTier riskTier, boolean isLoanRisky, boolean isApproved) {
        this();
        this.loan = loan;
        this.riskScore = riskScore;
        this.riskTier = riskTier;
        this.isLoanRisky = isLoanRisky;
        this.isApproved = isApproved;
    }

    public LoanActivityRecord(UUID id, Loan loan, double riskScore, RiskTier riskTier, boolean isLoanRisky, boolean isApproved) {
        this.id = id;
        this.loan = loan;
        this.riskScore = riskScore;
        this.riskTier = riskTier;
        this.isLoanRisky = isLoanRisky;
        this.isApproved = isApproved;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public RiskTier getRiskTier() {
        return riskTier;
    }

    public void setRiskTier(RiskTier riskTier) {
        this.riskTier = riskTier;
    }

    public boolean isLoanRisky() {
        return isLoanRisky;
    }

    public void setLoanRisky(boolean loanRisky) {
        isLoanRisky = loanRisky;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}

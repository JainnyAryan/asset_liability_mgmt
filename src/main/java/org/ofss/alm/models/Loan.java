package org.ofss.alm.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Loan {
    private UUID id;
    private LoanApplication loanApplication;
    private LocalDateTime createdAt;

    public Loan(){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Loan(LoanApplication loanApplication) {
        this();
        this.loanApplication = loanApplication;
    }

    public Loan(UUID id, LoanApplication loanApplication) {
        this.id = id;
        this.loanApplication = loanApplication;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}

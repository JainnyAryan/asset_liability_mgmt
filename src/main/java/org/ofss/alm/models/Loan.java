package org.ofss.alm.models;

import java.util.UUID;

public class Loan {
    private UUID id;
    private LoanApplication loanApplication;

    public Loan(){
        this.id = UUID.randomUUID();
    }

    public Loan(LoanApplication loanApplication) {
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

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}

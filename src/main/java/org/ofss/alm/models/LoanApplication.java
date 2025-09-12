package org.ofss.alm.models;


import org.ofss.alm.enums.LoanType;

import java.util.UUID;

public class LoanApplication {
    private UUID id;
    private Customer customer;
    private int creditScore;
    private double totalDebt;
    private int employmentYears;
    private boolean goodRepaymentHistory;
    private double income;
    private String educationLevel;
    private LoanType loanType;

    public LoanApplication(){
        this.id = UUID.randomUUID();
    }

    public LoanApplication(UUID id, Customer customer, int creditScore, double totalDebt, int employmentYears, boolean goodRepaymentHistory, double income, String educationLevel, LoanType loanType, double requestedAmount) {
        this.id = id;
        this.customer = customer;
        this.creditScore = creditScore;
        this.totalDebt = totalDebt;
        this.employmentYears = employmentYears;
        this.goodRepaymentHistory = goodRepaymentHistory;
        this.income = income;
        this.educationLevel = educationLevel;
        this.loanType = loanType;
        this.requestedAmount = requestedAmount;
    }

    public LoanApplication(Customer customer, int creditScore, double totalDebt, int employmentYears, boolean goodRepaymentHistory, double income, String educationLevel, LoanType loanType, double requestedAmount) {
        this();
        this.customer = customer;
        this.creditScore = creditScore;
        this.totalDebt = totalDebt;
        this.employmentYears = employmentYears;
        this.goodRepaymentHistory = goodRepaymentHistory;
        this.income = income;
        this.educationLevel = educationLevel;
        this.loanType = loanType;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "customer=" + customer.toString() +
                ", creditScore=" + creditScore +
                ", totalDebt=" + totalDebt +
                ", employmentYears=" + employmentYears +
                ", goodRepaymentHistory=" + goodRepaymentHistory +
                ", income=" + income +
                ", educationLevel='" + educationLevel + '\'' +
                ", loanType=" + loanType +
                ", requestedAmount=" + requestedAmount +
                '}';
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    private double requestedAmount;// e.g., "HighSchool", "Bachelor", "Master", etc.

    public LoanApplication(Customer customer, int creditScore, double totalDebt, int employmentYears, boolean goodRepaymentHistory, double income, String educationLevel) {
        this.customer = customer;
        this.creditScore = creditScore;
        this.totalDebt = totalDebt;
        this.employmentYears = employmentYears;
        this.goodRepaymentHistory = goodRepaymentHistory;
        this.income = income;
        this.educationLevel = educationLevel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public int getEmploymentYears() {
        return employmentYears;
    }

    public void setEmploymentYears(int employmentYears) {
        this.employmentYears = employmentYears;
    }

    public boolean isGoodRepaymentHistory() {
        return goodRepaymentHistory;
    }

    public void setGoodRepaymentHistory(boolean goodRepaymentHistory) {
        this.goodRepaymentHistory = goodRepaymentHistory;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Object getId() {
        return id;
    }
}

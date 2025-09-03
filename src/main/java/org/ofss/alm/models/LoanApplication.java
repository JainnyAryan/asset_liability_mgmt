package org.ofss.alm.models;


import org.ofss.alm.enums.LoanType;

public class LoanApplication {
    private Customer customer;
    private int creditScore;
    private double totalDebt;
    private int employmentYears;
    private boolean goodRepaymentHistory;
    private int age;
    private String educationLevel;
    private LoanType loanType;

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

    public LoanApplication(Customer customer, int creditScore, double totalDebt, int employmentYears, boolean goodRepaymentHistory, int age, String educationLevel) {
        this.customer = customer;
        this.creditScore = creditScore;
        this.totalDebt = totalDebt;
        this.employmentYears = employmentYears;
        this.goodRepaymentHistory = goodRepaymentHistory;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
}

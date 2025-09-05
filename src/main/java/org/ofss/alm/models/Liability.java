package org.ofss.alm.models;

import java.time.LocalDate;

public class Liability {
    private int liabilityId;
    private int customerId;
    private String liabilityType;
    private double principleAmount;
    private LocalDate annualInterestPayBackDate;
    private double interestRate;
    private String currencyCode;

    public Liability() {
    }

    public Liability(int liabilityId, int customerId, String liabilityType, double principleAmount, LocalDate annualInterestPayBackDate, double interestRate, String currencyCode) {
        this.liabilityId = liabilityId;
        this.customerId = customerId;
        this.liabilityType = liabilityType;
        this.principleAmount = principleAmount;
        this.annualInterestPayBackDate = annualInterestPayBackDate;
        this.interestRate = interestRate;
        this.currencyCode = currencyCode;
    }

    public int getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(int liabilityId) {
        this.liabilityId = liabilityId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLiabilityType() {
        return liabilityType;
    }

    public void setLiabilityType(String liabilityType) {
        this.liabilityType = liabilityType;
    }

    public double getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(double principleAmount) {
        this.principleAmount = principleAmount;
    }

    public LocalDate getAnnualInterestPayBackDate() {
        return annualInterestPayBackDate;
    }

    public void setAnnualInterestPayBackDate(LocalDate annualInterestPayBackDate) {
        this.annualInterestPayBackDate = annualInterestPayBackDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Liability{" +
                "liabilityId=" + liabilityId +
                ", customerId=" + customerId +
                ", liabilityType='" + liabilityType + '\'' +
                ", principleAmount=" + principleAmount +
                ", annualInterestPayBackDate=" + annualInterestPayBackDate +
                ", interestRate=" + interestRate +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}

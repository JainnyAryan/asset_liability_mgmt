package org.ofss.alm.models;

import java.time.LocalDate;

public class Asset {
    private int assetId;
    private int customerId;
    private String assetType;
    private double principleAmount;
    private LocalDate maturityDate;
    private double interestRate;
    private String currencyCode;

    public Asset() {
    }

    public Asset(int assetId, int customerId, String assetType, double principleAmount, LocalDate maturityDate, double interestRate, String currencyCode) {
        this.assetId = assetId;
        this.customerId = customerId;
        this.assetType = assetType;
        this.principleAmount = principleAmount;
        this.maturityDate = maturityDate;
        this.interestRate = interestRate;
        this.currencyCode = currencyCode;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public double getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(double principleAmount) {
        this.principleAmount = principleAmount;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
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

    public double getLiquidationRate(LocalDate currentDate) {
        if(this.assetType.equals("cash"))
        {
            return this.principleAmount;
        } else if (this.assetType.equals("bond")) {
            if(currentDate.isBefore(maturityDate))
            {
                return 0;
            }
            else
            {
                return this.principleAmount;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId=" + assetId +
                ", customerId=" + customerId +
                ", assetType='" + assetType + '\'' +
                ", principleAmount=" + principleAmount +
                ", maturityDate=" + maturityDate +
                ", interestRate=" + interestRate +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}

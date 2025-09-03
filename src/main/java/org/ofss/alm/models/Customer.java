package org.ofss.alm.models;

public class Customer {
    private Long customerId;
    private String name;
    private String type; // Individual, Corporate, etc.
    private String contactInfo;
    private double income;

    public void setIncome(double income) {
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    // Getters & Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getCreditScore() {
        return 0;
    }
}

package org.ofss.alm.models;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int customerId;
    private String type;
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setId(int customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "customer{" +
                "name='" + name + '\'' +
                ", id=" + customerId +
                ", type='" + type + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

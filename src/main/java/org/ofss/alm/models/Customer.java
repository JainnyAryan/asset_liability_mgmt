package org.ofss.alm.models;

public class Customer {
    private Long customerId;
    private String name;
    private String type; // Individual, Corporate, etc.
    private String contactInfo;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer(Long customerId, String name, String type, String contactInfo, int age) {
        this.customerId = customerId;
        this.name = name;
        this.type = type;
        this.contactInfo = contactInfo;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", age=" + age +
                '}';
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

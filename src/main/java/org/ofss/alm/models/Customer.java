package org.ofss.alm.models;

import java.util.UUID;

public class Customer {
    private UUID id;
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

    public Customer(){
        this.id = UUID.randomUUID();
    }

    public Customer(String name, String type, String contactInfo, int age) {
        this();
        this.name = name;
        this.type = type;
        this.contactInfo = contactInfo;
        this.age = age;
    }

    public Customer(UUID customerId, String name, String type, String contactInfo, int age) {
        this.id = customerId;
        this.name = name;
        this.type = type;
        this.contactInfo = contactInfo;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", age=" + age +
                '}';
    }

    // Getters & Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

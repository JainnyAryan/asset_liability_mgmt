package org.ofss.alm.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private String type; // Individual, Corporate, etc.
    private String email;
    private int age;
    private LocalDateTime createdAt;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer(){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Customer(String name, String type, String email, int age) {
        this();
        this.name = name;
        this.type = type;
        this.email = email;
        this.age = age;
    }

    public Customer(UUID customerId, String name, String type, String email, int age, LocalDateTime createdAt) {
        this.id = customerId;
        this.name = name;
        this.type = type;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", contactInfo='" + email + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreditScore() {
        return 0;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

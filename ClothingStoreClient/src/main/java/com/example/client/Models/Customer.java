package com.example.client.Models;

import com.example.client.Models.Enums.CustomerType;

public class Customer {
    private final String id;
    private String fullName;
    private String phoneNumber;
    private CustomerType type;

    // Constructors
    public Customer(String id, String fullName, String phoneNumber, CustomerType type) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    // Getters
    public String getId() { return id; }

    public String getFullName() { return fullName; }

    public String getPhoneNumber() { return phoneNumber; }

    public CustomerType getType() { return type; }

    // Setters
    public void setType(CustomerType type) { this.type = type; }
}

package com.example.client.Models;

import com.example.client.Models.Enums.EmployeeRole;

public class Employee {
    private final String id;
    private final int employeeNumber;
    private String password;
    private String fullName;
    private String phoneNumber;
    private int branchId;
    private String role;

    // Constructors
    public Employee(String id, int employeeNumber, String password, String role) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.password = password;
        this.role = role;
    }

    public Employee(String id, int employeeNumber, String password, String fullName, String phoneNumber, int branchId, String role) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.branchId = branchId;
        this.role = role;
    }

    // Getters
    public String getId() { return id; }
    public int getEmployeeNumber() { return employeeNumber; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getBranchId() { return branchId; }
    public String getRole() { return role; }

    // Setters
    public void setPassword(String password) { this.password = password; }
    public void setBranchId(int branchId) { this.branchId = branchId; }
    public void setRole(String role) { this.role = role; }
}
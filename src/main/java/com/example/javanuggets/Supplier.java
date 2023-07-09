package com.example.javanuggets;

public class Supplier {
    private String supplierName;
    private String contactNumber;
    private String location;
    private String email;

    public Supplier(String supplierName, String contactNumber, String location, String email) {
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.location = location;
        this.email = email;
    }

    // Getters and setters

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

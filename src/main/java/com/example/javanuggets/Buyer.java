package com.example.javanuggets;

public class Buyer {
    private String buyerName;
    private String contactNumber;
    private String email;

    public Buyer(String buyerName, String contactNumber, String email) {
        this.buyerName = buyerName;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getters and setters

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

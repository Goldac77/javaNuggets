package com.example.javanuggets;

public class Buyer {
    private String buyerName;
    private String contactNumber;

    public Buyer(String buyerName, String contactNumber) {
        this.buyerName = buyerName;
        this.contactNumber = contactNumber;
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

}

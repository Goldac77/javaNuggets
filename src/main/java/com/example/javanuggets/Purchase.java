package com.example.javanuggets;

import java.util.Date;

public class Purchase {
    private String purchaseID;
    private String drugID;
    private String buyerID;
    private Date purchaseDate;
    private int quantity;

    public Purchase(String purchaseID, String drugID, String buyerID, Date purchaseDate, int quantity) {
        this.purchaseID = purchaseID;
        this.drugID = drugID;
        this.buyerID = buyerID;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
    }

    // Getters and setters

    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getDrugID() {
        return drugID;
    }

    public void setDrugID(String drugID) {
        this.drugID = drugID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

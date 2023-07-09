package com.example.javanuggets;

public class Drug {
    private String drugName;
    private String supplierID;
    private double unitPrice;
    private int quantity;

    public Drug(String drugName, String supplierID, double unitPrice, int quantity) {
        this.drugName = drugName;
        this.supplierID = supplierID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // Getters and setters

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package com.example.javanuggets;

public class Drug {
    private static int count = 1;
    private String drugName;
    private String supplierID;
    private double unitPrice;
    private int quantity;
    private int id;

    public Drug(String drugName, String supplierID, double unitPrice, int quantity) {
        this.drugName = drugName;
        this.supplierID = supplierID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.id = count++;
    }

    // Getters and setters

    public int getId(){
        return this.id;
    }
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

package com.example.javanuggets;

public class DrugsData {
    private Integer supplierId;
    private String  drugName;
    private Double  price;
    private Integer quantity;

    public DrugsData(Integer supplierId, String drugName, Double price, Integer quantity) {
        this.supplierId = supplierId;
        this.drugName = drugName;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public String getDrugName() {
        return drugName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

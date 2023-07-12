package com.example.javanuggets;


import java.io.*;
import java.sql.*;
import java.util.*;


public class PharmacyManagementSystem {
    private Connection connection;
    private HashMap<String, Drug> drugs;
    private HashMap<String, Supplier> suppliers;
    private HashMap<String, Buyer> buyers;
    private ArrayList<Purchase> purchaseHistory;

    public PharmacyManagementSystem() {
        drugs = new HashMap<>();
        suppliers = new HashMap<>();
        buyers = new HashMap<>();
        purchaseHistory = new ArrayList<>();
    }

    private void loadDatabaseCredentials() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(".env")) {
            properties.load(input);
        }

        String url = properties.getProperty("DB_URL");
        String username = properties.getProperty("DB_USERNAME");
        String password = properties.getProperty("DB_PASSWORD");

        try {
            connectToDatabase(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Database connection setup
    public void connectToDatabase(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    // method for searching supplier collection by location
    public List<Supplier> searchSupplierByLocation(List<Supplier> suppliers, String location) {
        List<Supplier> result = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            if (supplier.getLocation().equalsIgnoreCase(location)) {
                result.add(supplier);
            }
        }
        return result;
    }

    // method for searching supplier collection by name
    public List<Supplier> searchSupplierByName(List<Supplier> suppliers, String supplierName) {
        List<Supplier> results = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierName().equalsIgnoreCase(supplierName)) {
                results.add(supplier);
            }
        }

        return results;
    }


    // method that sorts purchase history based on date
    public void sortPurchaseHistoryByDate(List<Purchase> purchaseHistory) {
        Collections.sort(purchaseHistory, new Comparator<Purchase>() {
            @Override
            public int compare(Purchase purchase1, Purchase purchase2) {
                return purchase1.getPurchaseDate().compareTo(purchase2.getPurchaseDate());
            }
        });
    }


}
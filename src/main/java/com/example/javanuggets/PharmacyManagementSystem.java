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

}

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


    // Database connection setup
    public void connectToDatabase(String url, String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully");
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

}

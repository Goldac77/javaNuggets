package com.example.javanuggets;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.HashMap;



public class PharmacyManagementSystem {
    private Connection connection;
    private HashMap<Integer, Drug> drugs;
    private HashMap<Integer, Supplier> suppliers;
    private HashMap<Integer, Buyer> buyers;
    private ArrayList<Purchase> purchaseHistory;

    //Creating just name and id parameters for now
    private HashMap<String, Integer> drugCollection;

    public PharmacyManagementSystem() {
        drugs = new HashMap<>();
        suppliers = new HashMap<>();
        buyers = new HashMap<>();
        purchaseHistory = new ArrayList<>();
    }

    //Function to search supplier by location
    public void searchSupplierByLocation(String location) {
        List<Integer> supplierId = new ArrayList<>();

        for (Supplier supplier : suppliers.values()) {
            if (supplier.getLocation().equalsIgnoreCase(location)) {
                supplierId.add(supplier.getId());
            }
        }

        for(int i = 0; i < supplierId.size(); i++){
            System.out.println(supplierId.get(i));
        }
    }

    //Function to add supplier to the hashtable
    public void addSupplier(String name, String contactNumber, String location, String email) {
        Supplier supplier = new Supplier(name, contactNumber, location, email);
        int supplierID = supplier.getId();
        suppliers.put(supplierID, supplier);
    }


    public void searchDrug(String drugName) {

        // Check if the drug is in the collection
        if (drugCollection.containsKey(drugName)) {
            int drugId = drugCollection.get(drugName);
            // Connect to the database and retrieve the drug information
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drug_db", "user", "password");
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM drugs WHERE id = " + drugId;
                ResultSet rs = stmt.executeQuery(sql);

                // Print the drug information from the collection and the database
                System.out.println("Drug information from the collection:");
                System.out.println(drugName + " ID: " + drugId);
                System.out.println("Drug information from the database:");
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " (ID: " + rs.getInt("id") + ")");
                }
                conn.close();
            } catch (SQLException e) {
                System.out.println("Database connection error: " + e.getMessage());
            }
        } else {
            System.out.println("Drug not found in collection.");
        }
    }

    //Method to add drugs, used only name and id, will update later
    public void addDrug(String drugName, int drugId ) {
        // Check if the drug is already in the collection
        if (drugCollection.containsKey(drugName)) {
            System.out.println("Drug already exists in collection.");
            return;
        }
        // Add the drug to the collection
        drugCollection.put(drugName, drugId);

        // Insert the drug data into the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drug_db", "user", "password");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO drugs (id, name) VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, drugId);
            pstmt.setString(2, drugName);

            pstmt.executeUpdate();
            System.out.println("Drug added to collection and database.");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    // Method to select a drug by its name and change its values in the collection and the database
    public void editDrug(String drugName, String name) {
        // Check if the drug is in the collection
        if (!drugCollection.containsKey(drugName)) {
            System.out.println("Drug not found in collection.");
            return;
        }
        int drugId = drugCollection.get(drugName);
        // Update the drug data in the collection
        drugCollection.put(drugName, drugId);
        // Update the drug data in the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drug_db", "user", "password");
            //Can only change name for now
            PreparedStatement pstmt = conn.prepareStatement("UPDATE drugs SET name = ? WHERE id = ?");
            pstmt.setString(1, name);
            pstmt.setInt(2, drugId);
            pstmt.executeUpdate();
            System.out.println("Drug data updated in collection and database.");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }


    public void deleteDrug(String drugName) {
        // Check if the drug is in the collection
        if (!drugCollection.containsKey(drugName)) {
            System.out.println("Drug not found in collection.");
            return;
        }
        int drugId = drugCollection.get(drugName);
        // Remove the drug from the collection
        drugCollection.remove(drugName);
        // Delete the drug data from the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drug_db", "user", "password");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM drugs WHERE id = ?");
            pstmt.setInt(1, drugId);
            pstmt.executeUpdate();
            System.out.println("Drug deleted from collection and database.");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
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

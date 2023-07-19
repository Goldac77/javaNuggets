package com.example.javanuggets;

import java.sql.PreparedStatement;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DashBoardController {

    @FXML
    private TextField AddPurchase_amount;

    @FXML
    private Button AddPurchase_buy;

    @FXML
    private TextField AddPurchase_buyerContact;

    @FXML
    private TextField AddPurchase_buyerName;

    @FXML
    private Button AddPurchase_cancel;

    @FXML
    private TextField AddPurchase_drugName;

    @FXML
    private AnchorPane AddPurchase_form;

    @FXML
    private TextField AddPurchase_price;

    @FXML
    private TextField AddPurchase_quantity;

    @FXML
    private TextField AddPurchase_total;

    @FXML
    private Button Delete_cancel;

    @FXML
    private Button Delete_deleteBtn;

    @FXML
    private TextField Delete_drugName;

    @FXML
    private AnchorPane Delete_form;

    @FXML
    private Button Drugs_add;

    @FXML
    private Button Drugs_delete;

    @FXML
    private AnchorPane Drugs_form;

    @FXML
    private TextField Drugs_search;

    @FXML
    private Button Drugs_tab;

    @FXML
    private TableView<?> Drugs_tableView;

    @FXML
    private TableColumn<?, ?> Drugs_tableView_col_drugName;

    @FXML
    private TableColumn<?, ?> Drugs_tableView_col_price;

    @FXML
    private TableColumn<?, ?> Drugs_tableView_col_quantity;

    @FXML
    private TableColumn<?, ?> Drugs_tableView_col_supplierID;

    @FXML
    private Button Drugs_update;

    @FXML
    private Button New_add;

    @FXML
    private Button New_cancel;

    @FXML
    private TextField New_drugName;

    @FXML
    private AnchorPane New_form;

    @FXML
    private TextField New_price;

   /* @FXML
    private Spinner<?> New_spinner;*/

    @FXML
    private Spinner<Integer> New_spinner;

    @FXML
    private TextField New_supplierName;

    @FXML
    private Button Purchase_tab;

    @FXML
    private Button Purchases_buy;

    @FXML
    private AnchorPane Purchases_form;

    @FXML
    private Button Purchases_receipt;

    @FXML
    private TableView<?> Purchases_tableView;

    @FXML
    private Button Sign_out;

    @FXML
    private TableView<?> Suppler_tableView;

    @FXML
    private TableColumn<?, ?> Suppler_tableView_col_email;

    @FXML
    private TableColumn<?, ?> Suppler_tableView_col_location;

    @FXML
    private TableColumn<?, ?> Suppler_tableView_col_supplierContact;

    @FXML
    private TableColumn<?, ?> Suppler_tableView_col_supplierID;

    @FXML
    private TableColumn<?, ?> Suppler_tableView_col_supplierName;

    @FXML
    private AnchorPane Supplier_form;

    @FXML
    private TextField Supplier_search;

    @FXML
    private Button Supplier_tab;

    @FXML
    private Button Update_cancel;

    @FXML
    private TextField Update_drugName;

    @FXML
    private AnchorPane Update_form;

    @FXML
    private TextField Update_price;

    @FXML
    private TextField Update_supplierID;

    @FXML
    private Button Update_updateBtn;

    @FXML
    private Button closeBtn;



    @FXML
    private void initialize() {
        // Set up the Spinner value factory from 0 - 100
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        New_spinner.setValueFactory(valueFactory);
    }

    @FXML
    private HashMap<Integer, Drug> drugHashTable;

    @FXML
    private HashMap<Integer, Supplier> supplierHashMap;

    public DashBoardController() {
        drugHashTable = new HashMap<>();
        // Spinner<?> newSpinner = new Spinner<>();
        supplierHashMap = new HashMap<>();

    }


    @FXML
    private void handleAddButtonAction() {
        // Retrieve values from text fields and spinner
        String supplierName = New_supplierName.getText();
        double price = Double.parseDouble(New_price.getText());
        String drugName = New_drugName.getText();
        int quantity = New_spinner.getValue(); // Not sure about spinner
        int supplierID = findSupplierKeyByName(supplierName);


        // Create a new drug object
        Drug newDrug = new Drug(drugName, supplierName, price, quantity);

        // Add drug object to the hash table
        drugHashTable.put(newDrug.getId(), newDrug);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Drugs Added");
        alert.setHeaderText(null);
        alert.setContentText("Drugs added successfully!");
        alert.showAndWait();

        // Clear text fields and spinner value
        New_supplierName.clear();
        New_price.clear();
        New_drugName.clear();
        New_spinner.getValueFactory().setValue(null);

        // inserting into drugs table
        String insertQuery = "INSERT INTO drugs (drug_id, drug_name, supplier_id, unit_price, quantity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try{
            // Prepare the statement
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, newDrug.getId());
            preparedStatement.setString(2, drugName);
            preparedStatement.setInt(3, supplierID); //  About to implement suppliers  hash table
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, quantity);
            // Execute the statement
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully!");

        }
        catch (SQLException e) {
            System.err.println("Error occurred while inserting data into the database: " + e.getMessage());
        }
        finally {
            // Close the prepared statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println("Error occurred while closing prepared statement: " + e.getMessage());
                }
            }

            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error occurred while closing database connection: " + e.getMessage());
                }
            }
        }

    }

    public int findSupplierKeyByName(String supplierName) {
        for (int key : supplierHashMap.keySet()) {
            Supplier supplier = supplierHashMap.get(key);
            if (supplier.getSupplierName().equals(supplierName)) {
                return key;
            }
        }
        return -1; // Return -1 if no matching supplier is found
    }




    //Database credentials
    String url = "jdbc:mysql://localhost:3306/pharmacy";
    String username = "root";
    String password = "PASSWORD";
    Connection connection;


    //Method to connect to Database
    public void connectToDatabase(String url, String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully");
        } catch (SQLException error) {
            System.out.println(error);
        }
    }


    //switching between screens
    public void switchForms(ActionEvent event){
        if(event.getSource() == Drugs_tab) {
            Drugs_form.setVisible(true);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);

            Drugs_tab.setStyle("-fx-background-color: linear-gradient(to bottom right, #19b999,#09948d);");
            Purchase_tab.setStyle("-fx-background: transparent");
            Supplier_tab.setStyle("-fx-background: transparent");
        } else if (event.getSource()== Purchase_tab) {
            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(true);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);

            Purchase_tab.setStyle("-fx-background-color: linear-gradient(to bottom right, #19b999,#09948d);");
            Drugs_tab.setStyle("-fx-background: transparent");
            Supplier_tab.setStyle("-fx-background: transparent");

        } else if (event.getSource()== Supplier_tab) {

            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(true);

            Supplier_tab.setStyle("-fx-background-color: linear-gradient(to bottom right, #19b999,#09948d);");
            Drugs_tab.setStyle("-fx-background: transparent");
            Purchase_tab.setStyle("-fx-background: transparent");
        }
    }


    public void Drugs_add(ActionEvent event){
        if(event.getSource() == Drugs_add){
            Drugs_form.setVisible(true);
            New_form.setVisible(true);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);
        } else if (event.getSource() == Drugs_update) {
            Drugs_form.setVisible(true);
            New_form.setVisible(false);
            Update_form.setVisible(true);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);
        } else if (event.getSource() == Drugs_delete) {
            Drugs_form.setVisible(true);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(true);
            Purchases_form.setVisible(false);
            AddPurchase_form.setVisible(false);
            Supplier_form.setVisible(false);
        }
    }

    public void drugsCancel(ActionEvent event){
        Drugs_form.setVisible(true);
        New_form.setVisible(false);
        Update_form.setVisible(false);
        Delete_form.setVisible(false);
        Purchases_form.setVisible(false);
        AddPurchase_form.setVisible(false);
        Supplier_form.setVisible(false);
    }

    public void PurchasesBuy(ActionEvent event){
        if(event.getSource() == Purchases_buy){
            Drugs_form.setVisible(false);
            New_form.setVisible(false);
            Update_form.setVisible(false);
            Delete_form.setVisible(false);
            Purchases_form.setVisible(true);
            AddPurchase_form.setVisible(true);
            Supplier_form.setVisible(false);
        }
    }

    public void addPurchaseCancel(ActionEvent event){
        Drugs_form.setVisible(false);
        New_form.setVisible(false);
        Update_form.setVisible(false);
        Delete_form.setVisible(false);
        Purchases_form.setVisible(true);
        AddPurchase_form.setVisible(false);
        Supplier_form.setVisible(false);
    }
    public void closeBtn(){
        System.exit(0);
    }

    public void Sign_out() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();

        Sign_out.getScene().getWindow().hide();
    }

    // method to add new drug to the database


}

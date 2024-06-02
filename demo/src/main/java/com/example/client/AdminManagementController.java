package com.example.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminManagementController {

    @FXML
    private ListView<String> parkingListView;

    @FXML
    private ListView<String> powerStationListView;

    @FXML
    private ListView<User> userListView;

    @FXML
    private TextField parkingNameField;

    @FXML
    private TextField parkingAvailabilityField;

    @FXML
    private TextField powerStationNameField;

    @FXML
    private TextField powerStationAvailabilityField;

    @FXML
    private Button addParkingButton;

    @FXML
    private Button updateParkingButton;

    @FXML
    private Button addPowerStationButton;

    @FXML
    private Button updatePowerStationButton;

    @FXML
    private Label messageLabel;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        loadParkingLots();
        loadPowerStations();
        loadUsers();

        addParkingButton.setOnAction(event -> addParkingLot());
        updateParkingButton.setOnAction(event -> updateParkingLot());
        addPowerStationButton.setOnAction(event -> addPowerStation());
        updatePowerStationButton.setOnAction(event -> updatePowerStation());
        backButton.setOnAction(event -> goBack());
    }

    private void loadParkingLots() {
        List<String> parkingLots = DatabaseUtil.getParkingLots();
        parkingListView.getItems().setAll(parkingLots);
    }

    private void loadPowerStations() {
        List<String> powerStations = DatabaseUtil.getPowerStations();
        powerStationListView.getItems().setAll(powerStations);
    }

    private void loadUsers() {
        List<User> users = DatabaseUtil.getUsers();
        userListView.getItems().setAll(users);
    }

    private void addParkingLot() {
        String name = parkingNameField.getText();
        int availability = Integer.parseInt(parkingAvailabilityField.getText());
        DatabaseUtil.insertParkingLot(name, availability);
        loadParkingLots();
        messageLabel.setText("Parking lot added successfully.");
    }

    private void updateParkingLot() {
        String name = parkingNameField.getText();
        int availability = Integer.parseInt(parkingAvailabilityField.getText());
        DatabaseUtil.updateParkingLotAvailability(name, availability);
        loadParkingLots();
        messageLabel.setText("Parking lot updated successfully.");
    }

    private void addPowerStation() {
        String name = powerStationNameField.getText();
        int availability = Integer.parseInt(powerStationAvailabilityField.getText());
        DatabaseUtil.insertPowerStation(name, availability);
        loadPowerStations();
        messageLabel.setText("Power station added successfully.");
    }

    private void updatePowerStation() {
        String name = powerStationNameField.getText();
        int availability = Integer.parseInt(powerStationAvailabilityField.getText());
        DatabaseUtil.updatePowerStationAvailability(name, availability);
        loadPowerStations();
        messageLabel.setText("Power station updated successfully.");
    }
    
     private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

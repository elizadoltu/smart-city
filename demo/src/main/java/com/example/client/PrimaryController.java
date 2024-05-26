package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class PrimaryController {

    @FXML
    private Label parkingLotsLabel;

    @FXML
    private Label powerStationsLabel;

    @FXML
    private Label usersLabel;

    public void initialize() {
        DatabaseUtil.initializeDatabase();
        DatabaseUtil.insertInitialData();
        displayParkingLots();
        displayPowerStations();
        displayUsers();
    }

    private void displayParkingLots() {
        List<String> parkingLots = DatabaseUtil.getParkingLots();
        StringBuilder parkingLotsText = new StringBuilder("Parking Lots:\n");
        for (String lot : parkingLots) {
            parkingLotsText.append("- ").append(lot).append("\n");
        }
        parkingLotsLabel.setText(parkingLotsText.toString());
    }

    private void displayPowerStations() {
        List<String> powerStations = DatabaseUtil.getPowerStations();
        StringBuilder powerStationsText = new StringBuilder("Power Stations:\n");
        for (String station : powerStations) {
            powerStationsText.append("- ").append(station).append("\n");
        }
        powerStationsLabel.setText(powerStationsText.toString());
    }

    private void displayUsers() {
        List<User> users = DatabaseUtil.getUsers();
        StringBuilder usersText = new StringBuilder("Users:\n");
        for (User user : users) {
            usersText.append("- ").append(user).append("\n");
        }
        usersLabel.setText(usersText.toString());
    }
}

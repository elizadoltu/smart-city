package com.example.client;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.List;

public class PrimaryController {

     private HostServices hostServices;

    @FXML
    private VBox parkingLotsVBox;

    @FXML
    private VBox powerStationsVBox;

    @FXML
    private Button backButton;

    // Initialize method to set up initial configurations
    public void initialize() {
        displayParkingLots();
        displayPowerStations();
        backButton.setOnAction(event -> goBack());
    }

    private void displayParkingLots() {
        List<String> parkingLots = DatabaseUtil.getParkingLots();
        for (String lot : parkingLots) {
            Button button = new Button(lot);
            button.setOnAction(event -> openParkingLotView(lot));
            parkingLotsVBox.getChildren().add(button);
        }
    }

    private void displayPowerStations() {
        List<String> powerStations = DatabaseUtil.getPowerStations();
        for (String station : powerStations) {
            Button button = new Button(station);
            button.setOnAction(event -> openPowerStationView(station));
            powerStationsVBox.getChildren().add(button);
        }
    }

    private void openParkingLotView(String parkingLot) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ParkingLotView.fxml"));
            Parent root = loader.load();
            ParkingLotController controller = loader.getController();
            controller.setParkingLot(parkingLot);
            
            Stage stage = (Stage) parkingLotsVBox.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openPowerStationView(String powerStation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PowerStationView.fxml"));
            Parent root = loader.load();
            PowerStationController controller = loader.getController();
            controller.setPowerStation(powerStation);

            Stage stage = (Stage) powerStationsVBox.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

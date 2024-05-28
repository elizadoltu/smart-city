package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ParkingLotController {

    @FXML
    private Label parkingLotLabel;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> goBack());
    }

    public void setParkingLot(String parkingLot) {
        parkingLotLabel.setText(parkingLot);
    }

    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

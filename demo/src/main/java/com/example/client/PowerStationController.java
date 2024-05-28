package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class PowerStationController {

    @FXML
    private Label powerStationLabel;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> goBack());
    }

    public void setPowerStation(String powerStation) {
        powerStationLabel.setText(powerStation);
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

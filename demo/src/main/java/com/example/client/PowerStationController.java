package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView powerStationImageView;

    @FXML
    private CheckBox chargingHereCheckBox;

    private String powerStationName;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> goBack());
        chargingHereCheckBox.setOnAction(event -> handleCheckBox());
    }

    public void setPowerStation(String powerStation) {
        powerStationName = powerStation.split(" - ")[0]; // Extract name
        powerStationLabel.setText(powerStation);
        // Construct the path to the image
        String imagePath = "/com/example/images/PowerStations/" + powerStationName + ".png";
        try {
            // Load the image
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            powerStationImageView.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + imagePath);
        }
    }

    private void handleCheckBox() {
        if (chargingHereCheckBox.isSelected()) {
            DatabaseUtil.updatePowerStationAvailability(powerStationName, -1);
        } else {
            DatabaseUtil.updatePowerStationAvailability(powerStationName, 1);
        }
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

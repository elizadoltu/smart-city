package com.example.client;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Map;

import com.example.client.DatabaseUtil;

public class ParkingLotController {

    @FXML
    private Label parkingLotLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button viewLocationButton;

    @FXML
    private ImageView parkingLotImageView;

    @FXML
    private CheckBox parkedHereCheckBox;

    @FXML
    private VBox statsVBox;

    private String parkingLotName;
    private HostServices hostServices;

    @FXML
    private void initialize() {
        hostServices = App.getHostServicesInstance(); // Get HostServices from the App class
        backButton.setOnAction(event -> goBack());
        viewLocationButton.setOnAction(event -> viewLocation());
        parkedHereCheckBox.setOnAction(event -> handleCheckBox());
    
    }

    public void setParkingLot(String parkingLot) {
        parkingLotName = parkingLot.split(" - ")[0]; // Extract name
        parkingLotLabel.setText(parkingLot);
        // Construct the path to the image
        String imagePath = "/com/example/images/ParkingLots/" + parkingLotName + ".png";
        try {
            // Load the image
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            parkingLotImageView.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + imagePath);
        }
    }

    private void handleCheckBox() {
        if (parkedHereCheckBox.isSelected()) {
            DatabaseUtil.updateParkingLotAvailability(parkingLotName, -1);
        } else {
            DatabaseUtil.updateParkingLotAvailability(parkingLotName, 1);
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

    private void viewLocation() {
        String query = parkingLotName.replace(" ", "+");
        String url = "https://www.google.com/maps/search/?api=1&query=" + query;
        if (hostServices != null) {
            hostServices.showDocument(url);
        } else {
            System.err.println("HostServices is not set. Cannot open URL.");
        }
    }

    
}

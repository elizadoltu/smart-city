package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private TextField messageField;
    @FXML
    private Label responseLabel;
    @FXML
    private Button sendButton;

    private Client client;

    public PrimaryController() {
        client = new Client("localhost", 8080);
    }

    @FXML
    private void initialize() {
        sendButton.setOnAction(event -> sendMessage());
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    private void sendMessage() {
        String message = messageField.getText();
        String response = client.sendMessage(message);
        responseLabel.setText(response);
    }
}

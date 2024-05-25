package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class PrimaryController {

    @FXML
    private Label usersLabel;

    public void initialize() {
        DatabaseUtil.initializeDatabase();
        DatabaseUtil.insertInitialUsers();
        displayUsers();
    }

    private void displayUsers() {
        List<String> users = DatabaseUtil.getUsers();
        StringBuilder usersText = new StringBuilder("Users:\n");
        for (String user : users) {
            usersText.append("- ").append(user).append("\n");
        }
        usersLabel.setText(usersText.toString());
    }
}

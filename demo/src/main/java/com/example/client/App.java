package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        String hostname = "localhost";
        int port = 8081; //am schimbat iar aici
        new Client(hostname, port);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(loader.load());
        
        // Corrected path to the CSS file
        scene.getStylesheets().add(getClass().getResource("/com/example/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Client App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.client;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static HostServices hostServices;
    
    @Override
    public void start(Stage stage) throws IOException {
        hostServices = getHostServices();

        String hostname = "localhost";
        int port = 8081;
        new Client(hostname, port);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
        Scene scene = new Scene(loader.load());

        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        Application.setUserAgentStylesheet(css);

        stage.setScene(scene);
        stage.setTitle("Client App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static HostServices getHostServicesInstance() {
        return hostServices;
    }

}
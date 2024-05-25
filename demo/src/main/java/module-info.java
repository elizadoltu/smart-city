module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.client to javafx.fxml, java.sql;

    exports com.example.client;
}

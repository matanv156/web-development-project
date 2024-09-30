module com.example.clothingstoreclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.client to javafx.fxml;
    exports com.example.client;

    exports com.example.client.Models;
    opens com.example.client.Models to com.google.gson, javafx.base, javafx.fxml;
}